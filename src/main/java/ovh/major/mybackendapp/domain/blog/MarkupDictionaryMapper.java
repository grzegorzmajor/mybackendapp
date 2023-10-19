package ovh.major.mybackendapp.domain.blog;

import ovh.major.mybackendapp.domain.blog.dto.MarkupDictionaryRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.MarkupDictionaryResponseDTO;

class MarkupDictionaryMapper {

    public static MarkupDictionaryEntity mapFromRequestDto(MarkupDictionaryRequestDTO requestDTO) {
        return MarkupDictionaryEntity.builder()
                .id(requestDTO.id())
                .tagName(requestDTO.tagName())
                .className(requestDTO.className())
                .build();
    }

    public static MarkupDictionaryResponseDTO mapToResponseDto(MarkupDictionaryEntity markupDictionary) {
        return MarkupDictionaryResponseDTO.builder()
                .id(markupDictionary.getId())
                .opening("</" + markupDictionary.getTagName() + " class=\"" + markupDictionary.getClassName() + "\">")
                .closing("</" + markupDictionary.getTagName() + ">")
                .build();
    }
}
