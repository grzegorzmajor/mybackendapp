package ovh.major.mybackendapp.domain.blog;

import ovh.major.mybackendapp.domain.blog.dto.ParagraphRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.ParagraphResponseDTO;
import ovh.major.mybackendapp.domain.blog.dto.PostRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.PostResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class PostMapper {
    public static PostEntity mapFromRequestDto(PostRequestDTO requestDTO) {

        List<ParagraphEntity> paragraphEntities = new ArrayList<>();

        paragraphEntities = listMapperFromRequestDto(requestDTO.paragraphs());

        return PostEntity.builder()

                .paragraphs(paragraphEntities)
                .build();
    }
    public static PostResponseDTO mapToResponseDto(PostEntity blogPost) {

        List<ParagraphResponseDTO> paragraphResponse = new ArrayList<>();

        paragraphResponse = listMapperToResponseDto(blogPost.getParagraphs());

        return PostResponseDTO.builder()
                .id(blogPost.getId())
                .addedDate(blogPost.getAddedDate())
                .paragraphs(paragraphResponse)
                .build();

    }

    private static List<ParagraphEntity> listMapperFromRequestDto(List<ParagraphRequestDTO> request ) {
        return request.stream()
                .map( paragraph -> {
                    return ParagraphMapper.mapFromRequestDto(paragraph);
                } )
                .collect(Collectors.toList());
    }

    private static List<ParagraphResponseDTO> listMapperToResponseDto(List<ParagraphEntity> entity ) {
        return entity.stream()
                .map( paragraph -> {
                    return ParagraphMapper.mapToResponseDto(paragraph);
                } )
                .collect(Collectors.toList());
    }
}
