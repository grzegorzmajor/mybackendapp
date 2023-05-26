package ovh.major.mybackendapp.domain.blog;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ovh.major.mybackendapp.domain.blog.dto.*;
import ovh.major.mybackendapp.domain.blog.logic.MarkupDictionaryService;
import ovh.major.mybackendapp.domain.blog.logic.ParagraphService;
import ovh.major.mybackendapp.domain.blog.logic.PostService;

import java.util.List;

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

    public void deleteMarkup(String id) {
        markupDictionaryService.deleteMarkup(id);
    }

    public ParagraphResponseDTO patchParagraph(ParagraphRequestDTO requestDTO) {
        return paragraphService.patchParagraph(requestDTO);
    }

    public ParagraphResponseDTO getParagraph(String id) {
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

}
