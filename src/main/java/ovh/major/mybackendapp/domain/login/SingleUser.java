package ovh.major.mybackendapp.domain.login;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public record SingleUser(

        String name,

        String hashedPassword
) {
        public SingleUser(@Value("${app_user.name}") String name, @Value("${app_user.hashedPassword}") String hashedPassword) {
                log.info("User " + name + " pass " + hashedPassword );
                this.name = name;
                this.hashedPassword = hashedPassword;
        }
}

