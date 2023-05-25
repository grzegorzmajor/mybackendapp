package ovh.major.mybackendapp.domain.blog.logic;

import ovh.major.mybackendapp.domain.blog.dto.MarkupDictionaryRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.MarkupDictionaryResponseDTO;

class MarkupDictionaryMapper {

    public static MarkupDictionaryEntity mapFromRequestDto(MarkupDictionaryRequestDTO requestDTO) {
        return MarkupDictionaryEntity.builder()
                .id(requestDTO.id())
                .opening(requestDTO.opening())
                .closing(requestDTO.closing())
                .build();
    }

    public static MarkupDictionaryResponseDTO mapToResponseDto(MarkupDictionaryEntity blogMarkupDictionary) {
        return MarkupDictionaryResponseDTO.builder()
                .id(blogMarkupDictionary.getId())
                .opening(blogMarkupDictionary.getOpening())
                .closing(blogMarkupDictionary.getClosing())
                .build();
    }

}
