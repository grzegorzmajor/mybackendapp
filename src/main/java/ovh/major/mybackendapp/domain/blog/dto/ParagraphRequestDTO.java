package ovh.major.mybackendapp.domain.blog.dto;

public record ParagraphRequestDTO(

        Integer id,
        MarkupDictionaryRequestDTO tag,
        String paragraphContent
) {
}
