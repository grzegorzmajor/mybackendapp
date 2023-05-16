package ovh.major.mybackendapp.infrastructure.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import ovh.major.mybackendapp.domain.login.dto.UserRequestDTO;
import ovh.major.mybackendapp.domain.login.dto.UserResponseDTO;

import java.time.*;

@AllArgsConstructor
@Component
public class JwtAuthenticatorFacade {

    private final AuthenticationManager authenticationManager;
    private final Clock clock;
    private final JwtConfigurationProperties properties;

    public UserResponseDTO authenticateAndGenerateToken(UserRequestDTO userRequestDto) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequestDto.name(), userRequestDto.password()));
        User user = (User) authenticate.getPrincipal();
        String token = createToken(user);
        String name = user.getUsername();
        return UserResponseDTO.builder()
                .token(token)
                .name(name)
                .build();
    }

    private String createToken(User user) {
        String secretKey = properties.secret();
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Instant now = LocalDateTime.now(clock).toInstant(ZoneOffset.UTC);
        Instant expiresAt = now.plus(Duration.ofDays(properties.expirationDays()));
        String issuer = properties.issuer();
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(expiresAt)
                .withIssuer(issuer)
                .sign(algorithm);
    }

}
