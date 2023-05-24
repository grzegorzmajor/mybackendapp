package ovh.major.mybackendapp.domain.blog;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ovh.major.mybackendapp.domain.blog.dto.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log4j2
@AllArgsConstructor
public class BlogFacade {

    private final PostRepository blogPostRepository;
    private final ParagraphRepository blogPostParagraphRepository;
    private final MarkupDictionaryRepository blogMarkupDictionaryRepository;


    public List<MarkupDictionaryResponseDTO> findAllMarkups() {
        List<MarkupDictionaryEntity> markups = StreamSupport.stream( blogMarkupDictionaryRepository.findAll().spliterator(), false)
                .toList();
        return markups.stream()
                .map(MarkupDictionaryMapper::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public MarkupDictionaryResponseDTO saveMarkup(MarkupDictionaryRequestDTO requestDTO) {
        return MarkupDictionaryMapper.mapToResponseDto(
                blogMarkupDictionaryRepository.save(
                        MarkupDictionaryMapper.mapFromRequestDto(requestDTO)
                )
        );

    }

    public void deleteMarkup(String id) {
        blogMarkupDictionaryRepository.deleteById(Integer.parseInt(id));
    }

    public List<PostResponseDTO> findAllPosts() {
        List<PostEntity> markups = StreamSupport.stream( blogPostRepository.findAll().spliterator(), false)
                .toList();
        return markups.stream()
                .map(PostMapper::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public Page<PostResponseDTO> findAllPostsPageable(Pageable pageable) {
        Page<PostEntity> postsPage = blogPostRepository.findAll(pageable);
        return postsPage.map(PostMapper::mapToResponseDto);
    }

    public PostResponseDTO savePost(PostRequestDTO requestDTO) {
        PostEntity postEntity =  PostMapper.mapFromRequestDto(requestDTO);

        postEntity.setParagraphs(
                findAndReturnListWithThemWhenExistInRepository(
                        postEntity.getParagraphs()
                ));

        postEntity.setAddedDate( new Timestamp( System.currentTimeMillis() ));

        return PostMapper.mapToResponseDto(
                blogPostRepository.save(postEntity)
        );
    }

    private List<ParagraphEntity> findAndReturnListWithThemWhenExistInRepository(List<ParagraphEntity> paragraphEntities) {
        return paragraphEntities.stream()
                .map(paragraph -> {
                    MarkupDictionaryEntity responseFromDatabaseTagEntity = blogMarkupDictionaryRepository.findFirstByOpening(
                            paragraph.getTag().getOpening());
                    if ( (responseFromDatabaseTagEntity != null) && ( responseFromDatabaseTagEntity.getId() > 0 ))  paragraph.setTag(responseFromDatabaseTagEntity);
                    return paragraph;
                }).toList();
    }

    public ParagraphResponseDTO patchParagraph(ParagraphRequestDTO requestDTO) {

        ParagraphEntity responseFromDatabaseParagraphEntity = blogPostParagraphRepository.findFirstById(requestDTO.id());

        ParagraphEntity postParagraphEntity = ParagraphMapper.mapFromRequestDto( requestDTO );

        responseFromDatabaseParagraphEntity.setTag(postParagraphEntity.getTag());
        responseFromDatabaseParagraphEntity.setParagraphContent(postParagraphEntity.getParagraphContent());

        MarkupDictionaryEntity responseFromDatabaseMarkupDictionaryEntity = blogMarkupDictionaryRepository.findFirstByOpening(responseFromDatabaseParagraphEntity.getTag().getOpening());

        responseFromDatabaseParagraphEntity.setTag(responseFromDatabaseMarkupDictionaryEntity);

        return ParagraphMapper.mapToResponseDto(blogPostParagraphRepository.save(responseFromDatabaseParagraphEntity));

    }

    public ParagraphResponseDTO getParagraph(String id) {
        return ParagraphMapper.mapToResponseDto( blogPostParagraphRepository.findFirstById(Integer.parseInt(id)));
    }


}
