package ovh.major.mybackendapp.domain.blog.logic;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ovh.major.mybackendapp.domain.blog.dto.ParagraphRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.ParagraphResponseDTO;

@Log4j2
@AllArgsConstructor
public class ParagraphService {

    private final MarkupDictionaryRepository markupDictionaryRepository;
    private final ParagraphRepository paragraphRepository;

    public ParagraphResponseDTO patchParagraph(ParagraphRequestDTO requestDTO) {

        ParagraphEntity responseFromDatabaseParagraphEntity = paragraphRepository.findFirstById(requestDTO.id());

        ParagraphEntity postParagraphEntity = ParagraphMapper.mapFromRequestDto(requestDTO);

        responseFromDatabaseParagraphEntity.setTag(postParagraphEntity.getTag());
        responseFromDatabaseParagraphEntity.setParagraphContent(postParagraphEntity.getParagraphContent());

        MarkupDictionaryEntity responseFromDatabaseMarkupDictionaryEntity =
                markupDictionaryRepository.findFirstByOpening(responseFromDatabaseParagraphEntity.getTag().getOpening());

        responseFromDatabaseParagraphEntity.setTag(responseFromDatabaseMarkupDictionaryEntity);

        return ParagraphMapper.mapToResponseDto(paragraphRepository.save(responseFromDatabaseParagraphEntity));
    }

    public ParagraphResponseDTO getParagraph(String id) {
        return ParagraphMapper.mapToResponseDto(paragraphRepository.findFirstById(Integer.parseInt(id)));
    }

}
