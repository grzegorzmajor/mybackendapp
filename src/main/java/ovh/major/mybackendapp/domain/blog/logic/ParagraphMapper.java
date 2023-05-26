package ovh.major.mybackendapp.domain.blog.logic;

import ovh.major.mybackendapp.domain.blog.dto.ParagraphRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.ParagraphResponseDTO;

class ParagraphMapper {

    public static ParagraphEntity mapFromRequestDto(ParagraphRequestDTO requestDTO) {
        return ParagraphEntity.builder()
                .id(requestDTO.id())
                .paragraphContent(requestDTO.paragraphContent())
                .tag(MarkupDictionaryMapper.mapFromRequestDto(
                        requestDTO.tag()))
                .build();
    }

    public static ParagraphResponseDTO mapToResponseDto(ParagraphEntity postParagraph) {
        return ParagraphResponseDTO.builder()
                .id(postParagraph.getId())
                .paragraphContent(postParagraph.getParagraphContent())
                .tag(MarkupDictionaryMapper.mapToResponseDto(
                        postParagraph.getTag()))
                .build();
    }
}
