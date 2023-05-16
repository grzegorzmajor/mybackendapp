package ovh.major.mybackendapp.domain.login;

import lombok.Builder;

@Builder
public record UserRequestDTO(
    String name,
    String password
){}
