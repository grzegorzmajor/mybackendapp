package ovh.major.mybackendapp.domain.blog.dto;

public record BlogPostParagraphRequestDTO(
    BlogMarkupDictionaryRequestDTO tag,
    String paragraphContent
) {
}
