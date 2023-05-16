package ovh.major.mybackendapp.domain.login.dto;

import lombok.Builder;

@Builder
public record SingleUserDTO(
        String name,
        String password
) {
}
