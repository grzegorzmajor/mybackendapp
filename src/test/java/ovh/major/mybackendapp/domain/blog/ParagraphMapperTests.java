package ovh.major.mybackendapp.domain.blog;

import org.junit.jupiter.api.Test;
import ovh.major.mybackendapp.domain.blog.dto.MarkupDictionaryRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.ParagraphRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.ParagraphResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ParagraphMapperTests {

    @Test
    void paragraphEntityIsCorrectMappedFromParagraphRequestDTO() {
        //given
        ParagraphRequestDTO paragraphRequestDTO = new ParagraphRequestDTO(
                1,
                new MarkupDictionaryRequestDTO(1, "<p>", "</p>"),
                string300CharactersLong());
        //when
        ParagraphEntity paragraphEntity = ParagraphMapper.mapFromRequestDto(paragraphRequestDTO);

        AtomicInteger sizeOffMappedParagraphContent = new AtomicInteger();
        paragraphEntity.getParagraphContent().forEach(line -> sizeOffMappedParagraphContent.addAndGet((line.length())));

        //then
        assertAll(
                () -> assertThat(sizeOffMappedParagraphContent.get()).isEqualTo(300),
                () -> assertThat(paragraphEntity.getParagraphContent().size()).isEqualTo(2),
                () -> assertThat(paragraphEntity.getParagraphContent().get(0).length()).isEqualTo(255),
                () -> assertThat(paragraphEntity.getId()).isEqualTo(paragraphRequestDTO.id()),
                () -> assertThat(paragraphEntity.getTag().getOpening()).isEqualTo(paragraphRequestDTO.tag().opening()),
                () -> assertThat(paragraphEntity.getTag().getClosing()).isEqualTo(paragraphRequestDTO.tag().closing()),
                () -> assertThat(paragraphEntity.getTag().getId()).isEqualTo(paragraphRequestDTO.tag().id())
        );
    }
    @Test
    void paragraphResponseDTOIsCorrectMappedFromParagraphEntity(){
        //given
        ParagraphEntity paragraphEntity = ParagraphEntity.builder()
                .id(1)
                .tag(MarkupDictionaryEntity.builder()
                        .id(1)
                        .opening("<h1>")
                        .closing("</h1>")
                        .build())
                .paragraphContent(listOf30StringsOf10CharactersEach())
                .build();
        //when
        ParagraphResponseDTO paragraphResponseDTO = ParagraphMapper.mapToResponseDto(paragraphEntity);

        //then
        assertAll(
                ()-> assertThat(paragraphResponseDTO.paragraphContent().length()).isEqualTo(300),
                ()-> assertThat(paragraphResponseDTO.id()).isEqualTo(paragraphEntity.getId()),
                ()-> assertThat(paragraphResponseDTO.tag().id()).isEqualTo(paragraphEntity.getTag().getId()),
                ()-> assertThat(paragraphResponseDTO.tag().opening()).isEqualTo(paragraphEntity.getTag().getOpening()),
                ()-> assertThat(paragraphResponseDTO.tag().closing()).isEqualTo(paragraphEntity.getTag().getClosing())
        );
    }

    private String string300CharactersLong() {
        return "1234567890".repeat(30);
    }

    private List<String> listOf30StringsOf10CharactersEach(){
        List<String> tempListOfStrings = new ArrayList<>();
        for (int i=0;i<30;i++) {
            tempListOfStrings.add("1234567890");
        }
        return tempListOfStrings;
    }

}
