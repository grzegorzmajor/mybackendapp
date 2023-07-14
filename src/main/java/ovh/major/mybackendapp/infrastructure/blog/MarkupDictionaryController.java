package ovh.major.mybackendapp.infrastructure.blog;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ovh.major.mybackendapp.domain.blog.BlogFacade;
import ovh.major.mybackendapp.domain.blog.dto.MarkupDictionaryRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.MarkupDictionaryResponseDTO;

import java.util.List;

@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping("/dict")
public class MarkupDictionaryController {

    BlogFacade blogFacade;

    @GetMapping
    public ResponseEntity<List<MarkupDictionaryResponseDTO>> findAllMarkups() {
        List<MarkupDictionaryResponseDTO> allMarkups = blogFacade.findAllMarkups();
        return ResponseEntity.ok(allMarkups);
    }

    @PostMapping
    public ResponseEntity<MarkupDictionaryResponseDTO> addMarkup(@RequestBody MarkupDictionaryRequestDTO requestDTO) {
        MarkupDictionaryResponseDTO responseDTO = blogFacade.saveMarkup(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMarkup(@RequestBody @PathVariable String id) {
        try {
            blogFacade.deleteMarkup(id);
        } catch (Exception e) {
            log.error("Exception in DELETE method - deleteMarkup: " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }


}
