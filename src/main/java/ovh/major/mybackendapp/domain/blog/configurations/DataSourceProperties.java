package ovh.major.mybackendapp.domain.blog.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:datasource.properties")
record DataSourceProperties (

    @Value("${url}")
    String url,

    @Value("${username}")
    String username,

    @Value("${password}")
    String password,

    @Value("${driverclassname}")
    String driverclassname

){}
