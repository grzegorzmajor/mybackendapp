package ovh.major.mybackendapp.domain.blog.dto;

import java.util.List;

public record BlogPostRequestDTO (
        Integer id,
        List<BlogPostParagraphRequestDTO> paragraphs
){}
