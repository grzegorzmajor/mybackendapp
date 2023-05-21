package ovh.major.mybackendapp.domain.blog.dto;

import lombok.Builder;

@Builder
public record BlogPostParagraphResponseDTO(
    Integer id,
    BlogMarkupDictionaryResponseDTO tag,
    String paragraphContent
) {
}
