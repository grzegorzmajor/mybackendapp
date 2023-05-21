package ovh.major.mybackendapp.infrastructure.blog;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ovh.major.mybackendapp.domain.blog.BlogFacade;
import ovh.major.mybackendapp.domain.blog.dto.BlogPostRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.BlogPostResponseDTO;

import java.util.List;

@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping("/posts")
public class BlogPostController {

    BlogFacade blogFacade;

    @GetMapping
    public ResponseEntity<List<BlogPostResponseDTO>> findAllPosts() {
        List<BlogPostResponseDTO> allPosts = blogFacade.findAllPosts() ;
        return ResponseEntity.ok(allPosts);
    }

    @PostMapping
    public ResponseEntity<BlogPostResponseDTO> addMarkup(@RequestBody BlogPostRequestDTO requestDTO) {
        BlogPostResponseDTO responseDTO = blogFacade.savePost(requestDTO) ;
        return ResponseEntity.ok(responseDTO);
    }

}
