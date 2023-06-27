package ovh.major.mybackendapp.domain.login;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:appuser.properties")
record SingleUser(

        @Value("${name}")
        String name,

        @Value("${hashedPassword}")
        String hashedPassword
) {
}

