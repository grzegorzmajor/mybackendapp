package ovh.major.mybackendapp.infrastructure.login;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ovh.major.mybackendapp.domain.login.LoginFacade;
import ovh.major.mybackendapp.domain.login.UserRequestDTO;
import ovh.major.mybackendapp.domain.login.UserResponseDTO;

@RestController
@Log4j2
@AllArgsConstructor
public class LoginController {

    private final LoginFacade loginFacade;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> authenticateUser(@RequestBody UserRequestDTO userRequestDto) {

        final UserResponseDTO userResponseDTO = loginFacade.authenticateUser(userRequestDto);

        if ( userResponseDTO.isLogged() ) {
            return ResponseEntity.ok(userResponseDTO);
        } else {
            return ResponseEntity
                    .status(HttpStatus.I_AM_A_TEAPOT)
                    .body(userResponseDTO);
        }
    }
}
