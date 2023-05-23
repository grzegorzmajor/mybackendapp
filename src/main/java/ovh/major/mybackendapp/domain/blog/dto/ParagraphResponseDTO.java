package ovh.major.mybackendapp.domain.blog.dto;

import lombok.Builder;

@Builder
public record ParagraphResponseDTO(
    Integer id,
    MarkupDictionaryResponseDTO tag,
    String paragraphContent
) {
}
