package ovh.major.mybackendapp.infrastructure.login;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ovh.major.mybackendapp.domain.login.dto.UserRequestDTO;
import ovh.major.mybackendapp.domain.login.dto.UserResponseDTO;
import ovh.major.mybackendapp.infrastructure.security.jwt.JwtAuthenticatorFacade;

@RestController
@Log4j2
@AllArgsConstructor
public class LoginController {

    private final JwtAuthenticatorFacade jwtAuthenticatorFacade;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> authenticateUser(@RequestBody UserRequestDTO userRequestDTO) {
        final UserResponseDTO userResponse = jwtAuthenticatorFacade.authenticateAndGenerateToken(userRequestDTO);
        return ResponseEntity.ok(userResponse);
    }
}
