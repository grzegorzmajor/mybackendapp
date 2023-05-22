package ovh.major.mybackendapp.domain.blog;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ovh.major.mybackendapp.domain.blog.dto.BlogMarkupDictionaryRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.BlogMarkupDictionaryResponseDTO;
import ovh.major.mybackendapp.domain.blog.dto.BlogPostRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.BlogPostResponseDTO;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log4j2
@AllArgsConstructor
public class BlogFacade {

    private final BlogPostRepository blogPostRepository;
    private final BlogPostParagraphRepository blogPostParagraphRepository;
    private final BlogMarkupDictionaryRepository blogMarkupDictionaryRepository;


    public List<BlogMarkupDictionaryResponseDTO> findAllMarkups() {
        List<BlogMarkupDictionaryEntity> markups = StreamSupport.stream( blogMarkupDictionaryRepository.findAll().spliterator(), false)
                .toList();
        return markups.stream()
                .map(BlogMarkupDictionaryMapper::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public BlogMarkupDictionaryResponseDTO saveMarkup(BlogMarkupDictionaryRequestDTO requestDTO) {
        return BlogMarkupDictionaryMapper.mapToResponseDto(
                blogMarkupDictionaryRepository.save(
                        BlogMarkupDictionaryMapper.mapFromRequestDto(requestDTO)
                )
        );

    }

    public void deleteMarkup(String id) {
        blogMarkupDictionaryRepository.deleteById(Integer.parseInt(id));
    }

    public List<BlogPostResponseDTO> findAllPosts() {
        List<BlogPostEntity> markups = StreamSupport.stream( blogPostRepository.findAll().spliterator(), false)
                .toList();
        return markups.stream()
                .map(BlogPostMapper::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public BlogPostResponseDTO savePost(BlogPostRequestDTO requestDTO) {
        BlogPostEntity postEntity =  BlogPostMapper.mapFromRequestDto(requestDTO);

        postEntity.setParagraphs(
                findAndReturnListWithThemWhenExistInRepository(
                        postEntity.getParagraphs()
                ));

        postEntity.setAddedDate( new Timestamp( System.currentTimeMillis() ));

        return BlogPostMapper.mapToResponseDto(
                blogPostRepository.save(postEntity)
        );
    }

    private List<BlogPostParagraphEntity> findAndReturnListWithThemWhenExistInRepository(List<BlogPostParagraphEntity> paragraphEntities) {
        return paragraphEntities.stream()
                .map(paragraph -> {
                    BlogMarkupDictionaryEntity responseFromDatabaseTagEntity = blogMarkupDictionaryRepository.findFirstByOpening(
                            paragraph.getTag().getOpening());
                    if ( (responseFromDatabaseTagEntity != null) && ( responseFromDatabaseTagEntity.getId() > 0 ))  paragraph.setTag(responseFromDatabaseTagEntity);
                    return paragraph;
                }).toList();
    }
}
