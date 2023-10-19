package ovh.major.mybackendapp.domain.blog.dto;

public record MarkupDictionaryRequestDTO(
        Integer id,
        String tagName,
        String className
) {
}
