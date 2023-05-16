package ovh.major.mybackendapp.domain.login;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public record SingleUser(

        String name,

        String hashedPassword
) {
        public SingleUser(@Value("${user.name}") String name, @Value("${user.hashedPassword}") String hashedPassword) {
                this.name = name;
                this.hashedPassword = hashedPassword;
        }

        public boolean isEqual(String name, String password, PasswordEncoder passwordEncoder) {
                boolean passIsEqual = passwordEncoder.matches(password,this.hashedPassword);
                return ((name.equals(this.name)) && passIsEqual );
        }
}

