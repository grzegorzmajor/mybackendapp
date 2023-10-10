package ovh.major.mybackendapp.domain.blog.infrastructure;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ovh.major.mybackendapp.domain.blog.BlogFacade;
import ovh.major.mybackendapp.domain.blog.dto.PostRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.PostResponseDTO;

@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping("/posts")
class PostController {

    BlogFacade blogFacade;

    @GetMapping(params = {"page", "size"})
    @CrossOrigin(
            origins = "*",
            methods = RequestMethod.GET
    )
    public ResponseEntity<Page<PostResponseDTO>> findAllForPublicationPostsPageable(Pageable pageable) {
        Page<PostResponseDTO> postsPage = blogFacade.findAllForPublicationPostsPageable(pageable);
        return ResponseEntity.ok(postsPage);
    }
    @GetMapping(value="/with-unpublished", params = {"page", "size"})
    public ResponseEntity<Page<PostResponseDTO>> findAllFPostsPageable(Pageable pageable) {
        Page<PostResponseDTO> postsPage = blogFacade.findAllPostsPageable(pageable);
        return ResponseEntity.ok(postsPage);
    }

    @PostMapping
    public ResponseEntity<PostResponseDTO> addPost(@RequestBody PostRequestDTO requestDTO) {
        PostResponseDTO responseDTO;
        responseDTO = blogFacade.savePost(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

}
