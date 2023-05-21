package ovh.major.mybackendapp.domain.blog;

import lombok.AllArgsConstructor;
import ovh.major.mybackendapp.domain.blog.dto.BlogMarkupDictionaryRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.BlogMarkupDictionaryResponseDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
}
