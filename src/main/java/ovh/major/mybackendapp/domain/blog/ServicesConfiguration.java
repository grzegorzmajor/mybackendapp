package ovh.major.mybackendapp.domain.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ServicesConfiguration {

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
                            PostRepository postRepository) {
        return new PostService(markupDictionaryRepository, postRepository);
    }
}
