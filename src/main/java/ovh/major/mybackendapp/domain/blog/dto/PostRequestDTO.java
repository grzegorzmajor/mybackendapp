package ovh.major.mybackendapp.domain.blog.dto;

import java.util.List;

public record PostRequestDTO(
        Integer id,
        List<ParagraphRequestDTO> paragraphs
) {
}
