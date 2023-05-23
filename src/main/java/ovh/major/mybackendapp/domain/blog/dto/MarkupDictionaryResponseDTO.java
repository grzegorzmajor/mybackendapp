package ovh.major.mybackendapp.domain.blog.dto;

import lombok.Builder;

@Builder
public record MarkupDictionaryResponseDTO(
    Integer id,
    String opening,
    String closing
) {}
