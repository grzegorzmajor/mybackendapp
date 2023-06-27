package ovh.major.mybackendapp.domain.blog.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
class DataSourceConfiguration {

    @Autowired
    DataSourceProperties dataSourceProperties;

    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName(dataSourceProperties.driverclassname())
                .url(dataSourceProperties.url())
                .username(dataSourceProperties.username())
                .password(dataSourceProperties.password())
                .build();
    }
}
