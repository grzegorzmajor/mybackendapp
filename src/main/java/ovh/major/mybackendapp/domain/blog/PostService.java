package ovh.major.mybackendapp.domain.blog;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import ovh.major.mybackendapp.domain.blog.dto.PostRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.PostResponseDTO;
import ovh.major.mybackendapp.domain.blog.infrastructure.exception.EntityWithIdNotExistException;
import ovh.major.mybackendapp.domain.blog.infrastructure.exception.NoUsedTagInDictDBException;
import ovh.major.mybackendapp.domain.blog.infrastructure.exception.PublicationDateNotValidException;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Log4j2
@AllArgsConstructor
class PostService {

    private final MarkupDictionaryRepository markupDictionaryRepository;
    private final PostRepository postRepository;

    @Transactional
    public Page<PostResponseDTO> findAllPostsPageable(Pageable pageable, boolean isForPublication) {
        Page<PostEntity> postsPage;
        Pageable updatedPageable = injectSortMethodAndReturnNewPageableObject(
                pageable,
                Sort.Direction.DESC,
                "addingDate"
        );
        if (isForPublication) {
            postsPage = postRepository.findAllWithPublicationDateTest(
                    new Timestamp(System.currentTimeMillis()),
                    updatedPageable
            );
        } else {
            postsPage = postRepository.findAll(updatedPageable);
        }
        return postsPage.map(PostMapper::mapToResponseDto);

    }

    @Transactional
    public PostResponseDTO savePost(PostRequestDTO requestDTO) {
        PostEntity postEntity = PostMapper.mapFromRequestDto(requestDTO);

        postEntity.setParagraphs(
                findAndReturnParagraphListWithThemWhenExistInRepository(
                        postEntity.getParagraphs()
                ));

        postEntity.setAddingDate(new Timestamp(System.currentTimeMillis()));

        return PostMapper.mapToResponseDto(
                postRepository.save(postEntity)
        );
    }

    @Transactional
    public PostResponseDTO patchPost(Integer id, Timestamp timestamp) {
        throwIfPostNotExistById(id);
        PostEntity inDbPostEntity = postRepository.findById(id)
                .orElseThrow();

        if (inDbPostEntity.getPublicationDate() == timestamp
                && timestamp.before(new Timestamp(System.currentTimeMillis()-3600000))) {
            throw new PublicationDateNotValidException();
        }
        Integer patchedId = postRepository.updatePublicationDate(id, timestamp);
        PostEntity postEntity = postRepository.findById(patchedId)
                .orElseThrow();
        return PostMapper.mapToResponseDto(postEntity);
    }

    @Transactional
    public PostResponseDTO findPostById(Integer id) {
        throwIfPostNotExistById(id);
        return PostMapper.mapToResponseDto(
                postRepository.findById(id)
                        .orElseThrow());
    }

    @Transactional
    public void deletePostById(Integer id) {
        throwIfPostNotExistById(id);
        postRepository.deleteById(id);
    }

    private void throwIfPostNotExistById(Integer id) {
        if (!postRepository.existsById(id)) {
            throw new EntityWithIdNotExistException(id);
        }
    }

    private List<ParagraphEntity> findAndReturnParagraphListWithThemWhenExistInRepository(List<ParagraphEntity> paragraphEntities) {
        return paragraphEntities.stream()
                .peek(paragraph -> {
                    MarkupDictionaryEntity responseFromDatabaseTagEntity = markupDictionaryRepository.findFirstByOpening(
                            paragraph.getTag().getOpening());
                    if (responseFromDatabaseTagEntity != null) {
                        paragraph.setTag(responseFromDatabaseTagEntity);
                    } else {
                        throw new NoUsedTagInDictDBException();
                    }
                }).toList();
    }

    private Pageable injectSortMethodAndReturnNewPageableObject(@NotNull Pageable pageable,
                                                                @NotNull Sort.Direction direction,
                                                                @NotNull String... properties) {
        Sort additionalSort = Sort.by(direction, properties);
        Sort currentSort = pageable.getSort();
        Sort mergedSort = currentSort.and(additionalSort);
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), mergedSort);
    }
}
