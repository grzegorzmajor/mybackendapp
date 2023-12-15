package ovh.major.mybackendapp.domain.blog.infrastructure;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ovh.major.mybackendapp.domain.blog.BlogFacade;
import ovh.major.mybackendapp.domain.blog.dto.ParagraphRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.ParagraphResponseDTO;

@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping("/paragraphs")
class ParagraphController {

    BlogFacade blogFacade;

    @CrossOrigin(
            origins = "*",
            methods = RequestMethod.PATCH
    )
    @PatchMapping
    public ResponseEntity<ParagraphResponseDTO> patchParagraph(@RequestBody ParagraphRequestDTO requestDTO) {
        ParagraphResponseDTO responseDTO = blogFacade.patchParagraph(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @CrossOrigin(
            origins = "*",
            methods = RequestMethod.GET
    )
    @GetMapping("/{id}")
    public ResponseEntity<ParagraphResponseDTO> getParagraph(@PathVariable Integer id) {
        ParagraphResponseDTO responseDTO = blogFacade.getParagraph(id);
        return ResponseEntity.ok(responseDTO);
    }
    @CrossOrigin(
            origins = "*",
            methods = RequestMethod.DELETE
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteParagraph(@PathVariable Integer id) {
        blogFacade.deleteParagraphById(id);
    }


}