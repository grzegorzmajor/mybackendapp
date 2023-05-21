package ovh.major.mybackendapp.domain.blog;

import ovh.major.mybackendapp.domain.blog.dto.BlogMarkupDictionaryRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.BlogMarkupDictionaryResponseDTO;

public class BlogMarkupDictionaryMapper {

    public static BlogMarkupDictionaryEntity mapFromRequestDto(BlogMarkupDictionaryRequestDTO requestDTO) {
        return BlogMarkupDictionaryEntity.builder()
                .opening(requestDTO.opening())
                .closing(requestDTO.closing())
                .build();
    }

    public static BlogMarkupDictionaryResponseDTO mapToResponseDto(BlogMarkupDictionaryEntity blogMarkupDictionary) {
        return BlogMarkupDictionaryResponseDTO.builder()
                .opening(blogMarkupDictionary.getOpening())
                .closing(blogMarkupDictionary.getClosing())
                .build();
    }

}
