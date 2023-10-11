package ovh.major.mybackendapp.domain.blog;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ovh.major.mybackendapp.domain.blog.dto.MarkupDictionaryRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.MarkupDictionaryResponseDTO;
import ovh.major.mybackendapp.domain.blog.infrastructure.exception.DependencyExistsException;
import ovh.major.mybackendapp.domain.blog.infrastructure.exception.DuplicatedTagInDictException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log4j2
@AllArgsConstructor
class MarkupDictionaryService {

    private final MarkupDictionaryRepository markupDictionaryRepository;
    private final ParagraphRepository paragraphRepository;

    public List<MarkupDictionaryResponseDTO> findAllMarkups() {
        List<MarkupDictionaryEntity> markups = StreamSupport.stream(markupDictionaryRepository.findAll().spliterator(), false)
                .toList();
        return markups.stream()
                .map(MarkupDictionaryMapper::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public MarkupDictionaryResponseDTO saveMarkup(MarkupDictionaryRequestDTO requestDTO) {
        MarkupDictionaryEntity markupDictionaryEntity = markupDictionaryRepository.findFirstByOpening(requestDTO.opening());
        if (markupDictionaryEntity != null) {
            throw new DuplicatedTagInDictException();
        }
        return MarkupDictionaryMapper.mapToResponseDto(
                markupDictionaryRepository.save(
                        MarkupDictionaryMapper.mapFromRequestDto(requestDTO)
                )
        );

    }

    public void deleteMarkup(Integer id) {
        if (paragraphRepository.checkDependencies(id)) {
            throw new DependencyExistsException();
        }
        markupDictionaryRepository.deleteById(id);
    }

}
