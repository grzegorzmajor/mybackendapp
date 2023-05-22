package ovh.major.mybackendapp.infrastructure.blog;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ovh.major.mybackendapp.domain.blog.BlogFacade;
import ovh.major.mybackendapp.domain.blog.dto.BlogPostParagraphRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.BlogPostParagraphResponseDTO;

@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping("/paragraphs")
public class BlogPostParagraphController {

    BlogFacade blogFacade;

    @PatchMapping
    public ResponseEntity patchParagraph(@RequestBody BlogPostParagraphRequestDTO requestDTO) {
        BlogPostParagraphResponseDTO responseDTO = blogFacade.patchParagraph(requestDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPostParagraphResponseDTO> getParagraph(@RequestBody @PathVariable String id) {
        BlogPostParagraphResponseDTO responseDTO = blogFacade.getParagraph(id);
        return ResponseEntity.ok(responseDTO);
    }
}