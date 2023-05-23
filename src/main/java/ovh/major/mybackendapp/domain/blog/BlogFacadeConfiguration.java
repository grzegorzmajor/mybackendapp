package ovh.major.mybackendapp.domain.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlogFacadeConfiguration {

    @Bean
    BlogFacade blogFacade(PostRepository blogPostRepository,
                          ParagraphRepository blogPostParagraphRepository,
                          MarkupDictionaryRepository blogMarkupDictionaryRepository){
        return new BlogFacade(blogPostRepository, blogPostParagraphRepository, blogMarkupDictionaryRepository);
    }

}
