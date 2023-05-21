package ovh.major.mybackendapp.domain.blog;

import ovh.major.mybackendapp.domain.blog.dto.BlogPostParagraphRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.BlogPostParagraphResponseDTO;
import ovh.major.mybackendapp.domain.blog.dto.BlogPostRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.BlogPostResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class BlogPostMapper {
    public static BlogPostEntity mapFromRequestDto(BlogPostRequestDTO requestDTO) {

        List<BlogPostParagraphEntity> paragraphEntities = new ArrayList<>();

        paragraphEntities = listMapperFromRequestDto(requestDTO.paragraphs());

        return BlogPostEntity.builder()

                .paragraphs(paragraphEntities)
                .build();
    }
    public static BlogPostResponseDTO mapToResponseDto(BlogPostEntity blogPost) {

        List<BlogPostParagraphResponseDTO> paragraphResponse = new ArrayList<>();

        paragraphResponse = listMapperToResponseDto(blogPost.getParagraphs());

        return BlogPostResponseDTO.builder()
                .id(blogPost.getId())
                .addedDate(blogPost.getAddedDate())
                .paragraphs(paragraphResponse)
                .build();

    }

    private static List<BlogPostParagraphEntity> listMapperFromRequestDto(List<BlogPostParagraphRequestDTO> request ) {
        return request.stream()
                .map( paragraph -> {
                    return BlogPostParagraphMapper.mapFromRequestDto(paragraph);
                } )
                .collect(Collectors.toList());
    }

    private static List<BlogPostParagraphResponseDTO> listMapperToResponseDto(List<BlogPostParagraphEntity> entity ) {
        return entity.stream()
                .map( paragraph -> {
                    return BlogPostParagraphMapper.mapToResponseDto(paragraph);
                } )
                .collect(Collectors.toList());
    }
}
