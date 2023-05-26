package ovh.major.mybackendapp.domain.blog.dto;

import java.util.Date;
import java.util.List;

public record PostRequestDTO(
        Integer id,
        Date publicationDate,
        List<ParagraphRequestDTO> paragraphs
) {
}
