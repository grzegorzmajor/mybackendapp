package ovh.major.mybackendapp.domain.login;


import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LoginFacade {

    private final SingleUser singleUser;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO authenticateUser(UserRequestDTO userRequestDto) {

        String hashedPassword = passwordEncoder.encode(userRequestDto.password());

        if ( userNameAndPasswordIsCorrect( userRequestDto.name(), userRequestDto.password() ) ) {
            return UserResponseDTO.builder()
                    .name(userRequestDto.name())
                    .isLogged( true )
                    .build();
        } else {
            return UserResponseDTO.builder()
                    .name(userRequestDto.name())
                    .isLogged( false )
                    .build();
        }
    }

    private boolean userNameAndPasswordIsCorrect(String name, String password) {
        boolean passIsEqual = passwordEncoder.matches(password, singleUser.hashedPassword());
        return ((name.equals(singleUser.name())) && passIsEqual );
    }
}
