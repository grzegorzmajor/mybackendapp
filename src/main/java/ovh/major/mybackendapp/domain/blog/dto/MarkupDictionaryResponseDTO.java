package ovh.major.mybackendapp.domain.blog.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record MarkupDictionaryResponseDTO(
        Integer id,
        String opening,
        String closing
) implements Serializable {
}
