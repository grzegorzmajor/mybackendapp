package ovh.major.mybackendapp.domain.blog.datasource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:datasource.properties")
record DataSourceProperties (

    @Value("${url}")
    String url,

    @Value("${name}")
    String username,

    @Value("${password}")
    String password,

    @Value("${driverclassname}")
    String driverclassname

){}
