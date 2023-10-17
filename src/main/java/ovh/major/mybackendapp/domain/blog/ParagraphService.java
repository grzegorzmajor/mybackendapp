package ovh.major.mybackendapp.domain.blog;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ovh.major.mybackendapp.domain.blog.dto.ParagraphRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.ParagraphResponseDTO;
import ovh.major.mybackendapp.domain.blog.infrastructure.exception.EntityWithIdNotExistException;
import ovh.major.mybackendapp.domain.blog.infrastructure.exception.NoUsedTagInDictDBException;
import ovh.major.mybackendapp.domain.blog.infrastructure.exception.NothingToChangeException;

@Log4j2
@AllArgsConstructor
class ParagraphService {

    @Autowired
    private final MarkupDictionaryRepository markupDictionaryRepository;

    @Autowired
    private final ParagraphRepository paragraphRepository;

    @Transactional
    public ParagraphResponseDTO patchParagraph(ParagraphRequestDTO requestDTO) {

        boolean isChanged = false;

        if (!paragraphRepository.existsById(requestDTO.id())) {
            throw new EntityWithIdNotExistException(requestDTO.id());
        }

        ParagraphEntity fromDatabaseParagraphEntity = paragraphRepository.findFirstById(requestDTO.id());

        ParagraphEntity newParagraphEntity = ParagraphMapper.mapFromRequestDto(requestDTO);

        if (!fromDatabaseParagraphEntity
                .getTag()
                .getOpening()
                .equals(newParagraphEntity
                        .getTag()
                        .getOpening())) {

            MarkupDictionaryEntity markupDictionaryEntity =
                    markupDictionaryRepository.findFirstByOpening(newParagraphEntity
                            .getTag()
                            .getOpening());

            if (markupDictionaryEntity == null) throw new NoUsedTagInDictDBException();

            fromDatabaseParagraphEntity.setTag(markupDictionaryEntity);
            isChanged = true;
        }

        if (!fromDatabaseParagraphEntity.getParagraphContent()
                .equals(newParagraphEntity.getParagraphContent())) {
            fromDatabaseParagraphEntity.setParagraphContent(
                    newParagraphEntity.getParagraphContent());

            isChanged = true;
        }

        if (!isChanged) throw new NothingToChangeException();

        return ParagraphMapper.mapToResponseDto(paragraphRepository.save(fromDatabaseParagraphEntity));
    }

    @Transactional
    public ParagraphResponseDTO getParagraph(Integer id) {

        if (!paragraphRepository.existsById(id)) {
            throw new EntityWithIdNotExistException(id);
        }

        return ParagraphMapper.mapToResponseDto(paragraphRepository.findFirstById(id));
    }

    @Transactional
    public void deleteParagraphById(Integer id) {

        if (!paragraphRepository.existsById(id)) {
            throw new EntityWithIdNotExistException(id);
        }

        paragraphRepository.deleteById(id);
    }
}
