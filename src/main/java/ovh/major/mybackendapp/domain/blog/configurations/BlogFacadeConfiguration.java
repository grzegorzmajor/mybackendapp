package ovh.major.mybackendapp.domain.blog.configurations;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ovh.major.mybackendapp.domain.blog.BlogFacade;
import ovh.major.mybackendapp.domain.blog.logic.MarkupDictionaryService;
import ovh.major.mybackendapp.domain.blog.logic.ParagraphService;
import ovh.major.mybackendapp.domain.blog.logic.PostService;

import javax.sql.DataSource;

@Configuration
@Log4j2
class BlogFacadeConfiguration {

    @Autowired
    private DBAccessData accessData;

    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName(accessData.driver())
                .url(accessData.url())
                .username(accessData.username())
                .password(accessData.password())
                .build();
    }

    @Bean
    BlogFacade blogFacade(PostService postService,
                          ParagraphService paragraphService,
                          MarkupDictionaryService markupDictionaryService) {
        return new BlogFacade(postService, paragraphService, markupDictionaryService);
    }



}
