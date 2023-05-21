package ovh.major.mybackendapp.domain.blog.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record BlogPostResponseDTO(
        Integer id,
        String addedDate,
        List<BlogPostParagraphResponseDTO> paragraphs
) {
}
