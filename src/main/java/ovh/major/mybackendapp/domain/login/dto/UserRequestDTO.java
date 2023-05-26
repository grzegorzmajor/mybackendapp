package ovh.major.mybackendapp.domain.login.dto;

import lombok.Builder;

@Builder
public record UserRequestDTO(
        String name,
        String password
) {
}
