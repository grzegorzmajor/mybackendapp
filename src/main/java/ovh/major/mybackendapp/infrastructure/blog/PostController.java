package ovh.major.mybackendapp.infrastructure.blog;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ovh.major.mybackendapp.domain.blog.BlogFacade;
import ovh.major.mybackendapp.domain.blog.dto.PostRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.PostResponseDTO;

import java.util.List;

@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping("/posts")
public class PostController {

    BlogFacade blogFacade;

    @GetMapping(params = {"!page", "!size"})
    public ResponseEntity<List<PostResponseDTO>> findAllPosts() {
        List<PostResponseDTO> allPosts = blogFacade.findAllPosts() ;
        return ResponseEntity.ok(allPosts);
    }
    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<PostResponseDTO>> findAllPostsPageable(Pageable pageable) {
        Page<PostResponseDTO> postsPage = blogFacade.findAllPostsPageable(pageable);
        return ResponseEntity.ok(postsPage);
    }

    @PostMapping
    public ResponseEntity<PostResponseDTO> addMarkup(@RequestBody PostRequestDTO requestDTO) {
        PostResponseDTO responseDTO = blogFacade.savePost(requestDTO) ;
        return ResponseEntity.ok(responseDTO);
    }

}
