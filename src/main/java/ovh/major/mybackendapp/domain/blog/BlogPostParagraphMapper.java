package ovh.major.mybackendapp.domain.blog;

import ovh.major.mybackendapp.domain.blog.dto.BlogPostParagraphRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.BlogPostParagraphResponseDTO;

public class BlogPostParagraphMapper {

    public static BlogPostParagraphEntity mapFromRequestDto(BlogPostParagraphRequestDTO requestDTO) {
        return BlogPostParagraphEntity.builder()
                .paragraphContent(requestDTO.paragraphContent())
                .tag(BlogMarkupDictionaryMapper.mapFromRequestDto(
                        requestDTO.tag()))
                .build();
    }
    public static BlogPostParagraphResponseDTO mapToResponseDto(BlogPostParagraphEntity blogPostParagraph) {
        return BlogPostParagraphResponseDTO.builder()
                .id(blogPostParagraph.getId())
                .paragraphContent(blogPostParagraph.getParagraphContent())
                .tag(BlogMarkupDictionaryMapper.mapToResponseDto(
                        blogPostParagraph.getTag()))
                .build();
    }
}
