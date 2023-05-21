package ovh.major.mybackendapp.domain.blog.dto;

public record BlogPostParagraphRequestDTO(
    BlogMarkupDictionaryResponseDTO tag,
    String paragraphContent
) {
}
