package ovh.major.mybackendapp.domain.blog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/dbaccess.properties")
public record DBAccessData(

    @Value("${url}")
    String url,

    @Value("${username}")
    String username,

    @Value("${password}")
    String password,

    @Value("${driver-class-name}")
    String driver
) {
}
