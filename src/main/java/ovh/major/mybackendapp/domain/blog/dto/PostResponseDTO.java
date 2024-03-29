package ovh.major.mybackendapp.domain.blog.dto;

import lombok.Builder;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Builder
public record PostResponseDTO (
        Integer id,
        Date addingDate,
        Date publicationDate,
        List<ParagraphResponseDTO> paragraphs
)  implements Serializable {
}
