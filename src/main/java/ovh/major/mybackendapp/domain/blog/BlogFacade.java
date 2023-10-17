package ovh.major.mybackendapp.domain.blog;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ovh.major.mybackendapp.domain.blog.dto.*;

import java.util.List;
import java.sql.Timestamp;

@Log4j2
@AllArgsConstructor
public class BlogFacade {

    private final PostService postService;
    private final ParagraphService paragraphService;
    private final MarkupDictionaryService markupDictionaryService;


    public List<MarkupDictionaryResponseDTO> findAllMarkups() {
        return markupDictionaryService.findAllMarkups();
    }

    public MarkupDictionaryResponseDTO saveMarkup(MarkupDictionaryRequestDTO requestDTO) {
        return markupDictionaryService.saveMarkup(requestDTO);
    }

    public ParagraphResponseDTO patchParagraph(ParagraphRequestDTO requestDTO) {
        return paragraphService.patchParagraph(requestDTO);
    }

    public ParagraphResponseDTO getParagraph(Integer id) {
        return paragraphService.getParagraph(id);
    }

    public Page<PostResponseDTO> findAllPostsPageable(Pageable pageable) {
        return postService.findAllPostsPageable(pageable, false);
    }

    public Page<PostResponseDTO> findAllForPublicationPostsPageable(Pageable pageable) {
        return postService.findAllPostsPageable(pageable, true);
    }

    public PostResponseDTO savePost(PostRequestDTO requestDTO) {
        return postService.savePost(requestDTO);
    }

    public PostResponseDTO patchPost(Integer postId, Timestamp timestamp){
        return postService.patchPost(postId, timestamp);
    }

    public PostResponseDTO findPostById(Integer id) {
        return postService.findPostById(id);
    }

    public void deletePostById(Integer id) {
        postService.deletePostById(id);
    }

    public void deleteParagraphById(Integer id) {
        paragraphService.deleteParagraphById(id);
    }
}
