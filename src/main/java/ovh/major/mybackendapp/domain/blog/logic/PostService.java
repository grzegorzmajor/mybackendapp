package ovh.major.mybackendapp.domain.blog.logic;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ovh.major.mybackendapp.domain.blog.dto.PostRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.PostResponseDTO;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log4j2
@AllArgsConstructor
public class PostService {

    private final MarkupDictionaryRepository markupDictionaryRepository;
    private final PostRepository postRepository;

    public List<PostResponseDTO> findAllPosts() {
        List<PostEntity> markups = StreamSupport.stream( postRepository.findAll().spliterator(), false)
                .toList();
        return markups.stream()
                .map(PostMapper::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public Page<PostResponseDTO> findAllPostsPageable(Pageable pageable) {
        Sort additionalSort = Sort.by(Sort.Direction.DESC,"addedDate" );
        Sort currentSort = pageable.getSort();
        Sort mergedSort = currentSort.and(additionalSort);
        Pageable updatedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), mergedSort);
        Page<PostEntity> postsPage = postRepository.findAll(updatedPageable);
        return postsPage.map(PostMapper::mapToResponseDto);
    }

    public PostResponseDTO savePost(PostRequestDTO requestDTO) {
        PostEntity postEntity =  PostMapper.mapFromRequestDto(requestDTO);

        postEntity.setParagraphs(
                findAndReturnParagraphListWithThemWhenExistInRepository(
                        postEntity.getParagraphs()
                ));

        postEntity.setAddedDate( new Timestamp( System.currentTimeMillis() ));

        return PostMapper.mapToResponseDto(
                postRepository.save(postEntity)
        );
    }

    private List<ParagraphEntity> findAndReturnParagraphListWithThemWhenExistInRepository(List<ParagraphEntity> paragraphEntities) {
        return paragraphEntities.stream()
                .map(paragraph -> {
                    MarkupDictionaryEntity responseFromDatabaseTagEntity = markupDictionaryRepository.findFirstByOpening(
                            paragraph.getTag().getOpening());
                    if ( (responseFromDatabaseTagEntity != null) && ( responseFromDatabaseTagEntity.getId() > 0 ))  paragraph.setTag(responseFromDatabaseTagEntity);
                    return paragraph;
                }).toList();
    }
}
