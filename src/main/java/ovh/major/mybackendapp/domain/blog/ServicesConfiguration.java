package ovh.major.mybackendapp.domain.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ServicesConfiguration {

    @Bean
    MarkupDictionaryService markupDictionaryService(MarkupDictionaryRepository markupDictionaryRepository,
                                                    ParagraphRepository paragraphRepository) {
        return new MarkupDictionaryService(markupDictionaryRepository, paragraphRepository);
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
