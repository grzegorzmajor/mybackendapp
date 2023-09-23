package ovh.major.mybackendapp.domain.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BlogFacadeConfiguration {

    @Bean
    BlogFacade blogFacade(PostService postService,
                          ParagraphService paragraphService,
                          MarkupDictionaryService markupDictionaryService) {
        return new BlogFacade(postService, paragraphService, markupDictionaryService);
    }

}
