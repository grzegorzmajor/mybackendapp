package ovh.major.mybackendapp.domain.blog.dto;

import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
public record PostResponseDTO(
        Integer id,
        Date addedDate,
        List<ParagraphResponseDTO> paragraphs
) {
}
