package ovh.major.mybackendapp.domain.blog.infrastructure;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ovh.major.mybackendapp.domain.blog.BlogFacade;
import ovh.major.mybackendapp.domain.blog.dto.PostRequestDTO;
import ovh.major.mybackendapp.domain.blog.dto.PostResponseDTO;

import java.sql.Timestamp;


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

    @CrossOrigin(
            origins = "*",
            methods = RequestMethod.GET
    )
    @GetMapping(value = "/with-unpublished", params = {"page", "size"})
    public ResponseEntity<Page<PostResponseDTO>> findAllFPostsPageable(Pageable pageable) {
        Page<PostResponseDTO> postsPage = blogFacade.findAllPostsPageable(pageable);
        return ResponseEntity.ok(postsPage);
    }

    @CrossOrigin(
            origins = "*",
            methods = RequestMethod.GET
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<PostResponseDTO> findPostById(@PathVariable Integer id) {
        PostResponseDTO post = blogFacade.findPostById(id);
        return ResponseEntity.ok(post);
    }

    @CrossOrigin(
            origins = "*",
            methods = RequestMethod.POST
    )
    @PostMapping
    public ResponseEntity<PostResponseDTO> addPost(@RequestBody PostRequestDTO requestDTO) {
        PostResponseDTO responseDTO = blogFacade.savePost(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @CrossOrigin(
            origins = "*",
            methods = RequestMethod.PATCH
    )
    @PatchMapping("/{id}")
    public ResponseEntity<PostResponseDTO> patchPost(@PathVariable Integer id, @RequestParam String timestamp) {
        PostResponseDTO responseDTO = blogFacade.patchPost(id, Timestamp.valueOf(timestamp.replace("T"," ")));
        return ResponseEntity.ok(responseDTO);
    }

    @CrossOrigin(
            origins = "*",
            methods = RequestMethod.DELETE
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Integer id) {
        blogFacade.deletePostById(id);
    }

}
