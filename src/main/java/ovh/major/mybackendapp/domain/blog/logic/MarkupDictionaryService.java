package ovh.major.mybackendapp.domain.blog.logic;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ovh.major.mybackendapp.domain.blog.dto.MarkupDictionaryRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.MarkupDictionaryResponseDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log4j2
@AllArgsConstructor
public class MarkupDictionaryService {

    private final MarkupDictionaryRepository markupDictionaryRepository;

    public List<MarkupDictionaryResponseDTO> findAllMarkups() {
        List<MarkupDictionaryEntity> markups = StreamSupport.stream(markupDictionaryRepository.findAll().spliterator(), false)
                .toList();
        return markups.stream()
                .map(MarkupDictionaryMapper::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public MarkupDictionaryResponseDTO saveMarkup(MarkupDictionaryRequestDTO requestDTO) {
        return MarkupDictionaryMapper.mapToResponseDto(
                markupDictionaryRepository.save(
                        MarkupDictionaryMapper.mapFromRequestDto(requestDTO)
                )
        );

    }

    public void deleteMarkup(String id) {
        markupDictionaryRepository.deleteById(Integer.parseInt(id));
    }

}
