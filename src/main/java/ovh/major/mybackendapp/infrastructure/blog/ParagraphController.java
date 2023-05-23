package ovh.major.mybackendapp.infrastructure.blog;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ovh.major.mybackendapp.domain.blog.BlogFacade;
import ovh.major.mybackendapp.domain.blog.dto.ParagraphRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.ParagraphResponseDTO;

@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping("/paragraphs")
public class ParagraphController {

    BlogFacade blogFacade;

    @PatchMapping
    public ResponseEntity patchParagraph(@RequestBody ParagraphRequestDTO requestDTO) {
        ParagraphResponseDTO responseDTO = blogFacade.patchParagraph(requestDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParagraphResponseDTO> getParagraph(@RequestBody @PathVariable String id) {
        ParagraphResponseDTO responseDTO = blogFacade.getParagraph(id);
        return ResponseEntity.ok(responseDTO);
    }
}