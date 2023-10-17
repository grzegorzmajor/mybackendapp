package ovh.major.mybackendapp.domain.blog.infrastructure;

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
class MarkupDictionaryController {

    BlogFacade blogFacade;

    @GetMapping
    public ResponseEntity<List<MarkupDictionaryResponseDTO>> findAllMarkups() {
        List<MarkupDictionaryResponseDTO> allMarkups = blogFacade.findAllMarkups();
        return ResponseEntity.ok(allMarkups);
    }

    @PostMapping
    public ResponseEntity<MarkupDictionaryResponseDTO> addMarkup(@RequestBody MarkupDictionaryRequestDTO requestDTO) {
        MarkupDictionaryResponseDTO responseDTO;
        responseDTO = blogFacade.saveMarkup(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

}
