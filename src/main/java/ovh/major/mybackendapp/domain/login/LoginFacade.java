package ovh.major.mybackendapp.domain.login;


import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import ovh.major.mybackendapp.domain.login.dto.SingleUserDTO;


@Component
@AllArgsConstructor
public class LoginFacade {

    private final SingleUser singleUser;

    public SingleUserDTO findByName(String username) {
        if (singleUser.name().equals(username)) {
            return SingleUserDTO.builder()
                    .name(singleUser.name())
                    .password(singleUser.hashedPassword())
                    .build();
        } else {
            throw new BadCredentialsException("User not found");
        }
    }
}
