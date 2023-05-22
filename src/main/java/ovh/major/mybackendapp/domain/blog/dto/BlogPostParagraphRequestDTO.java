package ovh.major.mybackendapp.domain.blog.dto;

public record BlogPostParagraphRequestDTO(

        Integer id,
        BlogMarkupDictionaryRequestDTO tag,
        String paragraphContent
) {
}
