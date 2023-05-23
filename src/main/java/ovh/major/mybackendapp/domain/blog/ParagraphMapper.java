package ovh.major.mybackendapp.domain.blog;

import ovh.major.mybackendapp.domain.blog.dto.ParagraphRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.ParagraphResponseDTO;

public class ParagraphMapper {

    public static ParagraphEntity mapFromRequestDto(ParagraphRequestDTO requestDTO) {
        return ParagraphEntity.builder()
                .id(requestDTO.id())
                .paragraphContent(requestDTO.paragraphContent())
                .tag(MarkupDictionaryMapper.mapFromRequestDto(
                        requestDTO.tag()))
                .build();
    }
    public static ParagraphResponseDTO mapToResponseDto(ParagraphEntity blogPostParagraph) {
        return ParagraphResponseDTO.builder()
                .id(blogPostParagraph.getId())
                .paragraphContent(blogPostParagraph.getParagraphContent())
                .tag(MarkupDictionaryMapper.mapToResponseDto(
                        blogPostParagraph.getTag()))
                .build();
    }
}
