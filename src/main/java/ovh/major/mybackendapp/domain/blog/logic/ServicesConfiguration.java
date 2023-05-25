package ovh.major.mybackendapp.domain.blog.logic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfiguration {

    @Bean
    MarkupDictionaryService markupDictionaryService(MarkupDictionaryRepository markupDictionaryRepository) {
        return new MarkupDictionaryService(markupDictionaryRepository);
    }

    @Bean
    ParagraphService paragraphService(MarkupDictionaryRepository markupDictionaryRepository,
                                      ParagraphRepository paragraphRepository) {
        return new ParagraphService(markupDictionaryRepository, paragraphRepository);
    }

    @Bean
    PostService postService(MarkupDictionaryRepository markupDictionaryRepository,
                            ParagraphRepository paragraphRepository,
                            PostRepository postRepository) {
        return new PostService(markupDictionaryRepository,paragraphRepository, postRepository);
    }
}
