package ovh.major.mybackendapp.domain.blog;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
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
        throwIfParagraphWithIdNotExist(requestDTO.id());
        ParagraphEntity fromDatabaseParagraphEntity = paragraphRepository.findFirstById(requestDTO.id());
        ParagraphEntity newParagraphEntity = ParagraphMapper.mapFromRequestDto(requestDTO);
        MarkupDictionaryEntity oldMarkupDictionaryEntity = fromDatabaseParagraphEntity.getTag();

        if (theyHaveDifferentTags(fromDatabaseParagraphEntity, newParagraphEntity)) {
            MarkupDictionaryEntity markupDictionaryEntity =
                    markupDictionaryRepository.findFirstByTagName(newParagraphEntity
                            .getTag()
                            .getTagName());
            if (markupDictionaryEntity == null) { throw new NoUsedTagInDictDBException(); }
            fromDatabaseParagraphEntity.setTag(markupDictionaryEntity);
            isChanged = true;
        }

        if (theyHaveDifferentContent(fromDatabaseParagraphEntity, newParagraphEntity)) {
            fromDatabaseParagraphEntity.setParagraphContent(
                    newParagraphEntity.getParagraphContent());
            isChanged = true;
        }

        if (!isChanged) { throw new NothingToChangeException(); }
        ParagraphEntity savedParagraphEntity = paragraphRepository.save(fromDatabaseParagraphEntity);
        deleteMarkupIfDependenciesNotExist(oldMarkupDictionaryEntity.getId());
        return ParagraphMapper.mapToResponseDto(savedParagraphEntity);
    }



    @Transactional
    public ParagraphResponseDTO getParagraph(Integer id) {
        throwIfParagraphWithIdNotExist(id);
        return ParagraphMapper.mapToResponseDto(paragraphRepository.findFirstById(id));
    }

    @Transactional
    public void deleteParagraphById(Integer id) {
        throwIfParagraphWithIdNotExist(id);
        ParagraphEntity deletingParagraph = paragraphRepository.findFirstById(id);
        paragraphRepository.deleteById(id);
        deleteMarkupIfDependenciesNotExist(deletingParagraph.getTag().getId());
    }

    private static boolean theyHaveDifferentTags(ParagraphEntity fromDatabaseParagraphEntity, ParagraphEntity newParagraphEntity) {
        return !fromDatabaseParagraphEntity
                .getTag()
                .getTagName()
                .equals(newParagraphEntity
                        .getTag()
                        .getTagName());
    }

    private static boolean theyHaveDifferentContent(ParagraphEntity fromDatabaseParagraphEntity, ParagraphEntity newParagraphEntity) {
        return !fromDatabaseParagraphEntity.getParagraphContent()
                .equals(newParagraphEntity.getParagraphContent());
    }
    private void throwIfParagraphWithIdNotExist(Integer id) {
        if (!paragraphRepository.existsById(id)) {
            throw new EntityWithIdNotExistException(id);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteMarkupIfDependenciesNotExist(Integer deletingParagraph) {
        if (!paragraphRepository.checkDependencies(deletingParagraph)) {
            markupDictionaryRepository.deleteById(deletingParagraph);
        }
    }
}
