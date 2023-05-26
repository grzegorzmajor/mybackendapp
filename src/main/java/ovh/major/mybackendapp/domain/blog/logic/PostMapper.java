package ovh.major.mybackendapp.domain.blog.logic;

import ovh.major.mybackendapp.domain.blog.dto.ParagraphRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.ParagraphResponseDTO;
import ovh.major.mybackendapp.domain.blog.dto.PostRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.PostResponseDTO;

import java.util.List;
import java.util.stream.Collectors;


class PostMapper {
    public static PostEntity mapFromRequestDto(PostRequestDTO requestDTO) {

        List<ParagraphEntity> paragraphEntities = listMapperFromRequestDto(requestDTO.paragraphs());

        return PostEntity.builder()
                .paragraphs(paragraphEntities)
                .build();
    }

    public static PostResponseDTO mapToResponseDto(PostEntity blogPost) {

        List<ParagraphResponseDTO> paragraphResponse = listMapperToResponseDto(blogPost.getParagraphs());

        return PostResponseDTO.builder()
                .id(blogPost.getId())
                .addedDate(blogPost.getAddedDate())
                .paragraphs(paragraphResponse)
                .build();

    }

    private static List<ParagraphEntity> listMapperFromRequestDto(List<ParagraphRequestDTO> request) {
        return request.stream()
                .map(ParagraphMapper::mapFromRequestDto)
                .collect(Collectors.toList());
    }

    private static List<ParagraphResponseDTO> listMapperToResponseDto(List<ParagraphEntity> entity) {
        return entity.stream()
                .map(ParagraphMapper::mapToResponseDto)
                .collect(Collectors.toList());
    }
}
