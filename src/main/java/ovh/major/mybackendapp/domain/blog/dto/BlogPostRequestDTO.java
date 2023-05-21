package ovh.major.mybackendapp.domain.blog.dto;

import java.util.List;

public record BlogPostRequestDTO (

        List<BlogPostParagraphRequestDTO> paragraphs
){}
