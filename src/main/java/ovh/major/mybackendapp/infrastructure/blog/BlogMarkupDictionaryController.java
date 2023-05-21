package ovh.major.mybackendapp.infrastructure.blog;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ovh.major.mybackendapp.domain.blog.BlogFacade;
import ovh.major.mybackendapp.domain.blog.dto.BlogMarkupDictionaryRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.BlogMarkupDictionaryResponseDTO;

import java.util.List;

@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping("/dict")
public class BlogMarkupDictionaryController {

    BlogFacade blogFacade;

    @GetMapping
    public ResponseEntity<List<BlogMarkupDictionaryResponseDTO>> findAllMarkups() {
        List<BlogMarkupDictionaryResponseDTO> allMarkups = blogFacade.findAllMarkups() ;
        return ResponseEntity.ok(allMarkups);
    }
    @PostMapping
    public ResponseEntity<BlogMarkupDictionaryResponseDTO> addMarkup(@RequestBody BlogMarkupDictionaryRequestDTO requestDTO) {
        BlogMarkupDictionaryResponseDTO responseDTO = blogFacade.saveMarkup(requestDTO) ;
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/{id}")
    public ResponseEntity deleteMarkup(@RequestBody @PathVariable String id) {
        try {
            blogFacade.deleteMarkup(id);
        } catch (Exception e) {
            log.error("Exception in POST method - deleteMarkup: " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }


}
