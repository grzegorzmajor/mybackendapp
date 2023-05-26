package ovh.major.mybackendapp.domain.login.dto;

import lombok.Builder;

@Builder
public record UserResponseDTO(
        String token,
        String name
) {
}
