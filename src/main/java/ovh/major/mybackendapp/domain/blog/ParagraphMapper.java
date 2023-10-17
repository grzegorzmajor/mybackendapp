package ovh.major.mybackendapp.domain.blog;

import lombok.extern.log4j.Log4j2;
import ovh.major.mybackendapp.domain.blog.dto.ParagraphRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.ParagraphResponseDTO;

import java.util.ArrayList;
import java.util.List;

@Log4j2
class ParagraphMapper {

    public static ParagraphEntity mapFromRequestDto(ParagraphRequestDTO requestDTO) {
        return ParagraphEntity.builder()
                .id(requestDTO.id())
                .paragraphContent(
                        fragmentIntoAListOfStringsOfLength255(requestDTO.paragraphContent()))
                .tag(MarkupDictionaryMapper.mapFromRequestDto(
                        requestDTO.tag()))
                .build();
    }

    public static ParagraphResponseDTO mapToResponseDto(ParagraphEntity postParagraph) {
        return ParagraphResponseDTO.builder()
                .id(postParagraph.getId())
                .paragraphContent(
                        concatenateAListOfStringsIntoOneString(postParagraph.getParagraphContent()))
                .tag(MarkupDictionaryMapper.mapToResponseDto(
                        postParagraph.getTag()))
                .build();
    }

    private static List<String> fragmentIntoAListOfStringsOfLength255(String sourceString) {
        String temporaryString = sourceString;
        List<String> fragmentedParagraph = new ArrayList<>();
        while (temporaryString.length()>0) {
            if (temporaryString.length()>255) {
                fragmentedParagraph.add(temporaryString.substring(0, 255));
                temporaryString = temporaryString.substring(255);
            } else {
                fragmentedParagraph.add(temporaryString);
                temporaryString = "";
            }
        }
        return fragmentedParagraph;
    }

    private static String concatenateAListOfStringsIntoOneString(List<String> source) {
        // warunek zakończenia rekurencji - zwrócenie ostatniego elementu listy
        if (source.size() == 1) {
            return source.get(0);
        }

        // zwrócenie pierwszego elementu listy i dołączenie reszty poprzez rekurencyjne wywołanie funkcji
        // w efekcie po zakończeniu wywołań rekurencyjnych lista będzie się niejako łączyć "od tyłu"
        return (
                source.get(0) +
                concatenateAListOfStringsIntoOneString(source.subList(1,source.size()))
        );
    }
}
