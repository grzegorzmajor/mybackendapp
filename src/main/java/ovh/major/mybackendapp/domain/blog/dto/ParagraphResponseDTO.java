package ovh.major.mybackendapp.domain.blog.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record ParagraphResponseDTO(
        Integer id,
        MarkupDictionaryResponseDTO tag,
        String paragraphContent
) implements Serializable {
}
