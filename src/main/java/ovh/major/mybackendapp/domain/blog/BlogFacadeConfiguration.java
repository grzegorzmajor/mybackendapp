package ovh.major.mybackendapp.domain.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ovh.major.mybackendapp.domain.blog.logic.MarkupDictionaryService;
import ovh.major.mybackendapp.domain.blog.logic.ParagraphService;
import ovh.major.mybackendapp.domain.blog.logic.PostService;

@Configuration
public class BlogFacadeConfiguration {

    @Bean
    BlogFacade blogFacade(PostService postService,
                          ParagraphService paragraphService,
                          MarkupDictionaryService markupDictionaryService){
        return new BlogFacade(postService, paragraphService, markupDictionaryService);
    }

}
