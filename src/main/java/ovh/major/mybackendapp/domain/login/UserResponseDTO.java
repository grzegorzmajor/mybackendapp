package ovh.major.mybackendapp.domain.login;

import lombok.Builder;

@Builder
public record UserResponseDTO (
        String name,
        boolean isLogged
){
}
