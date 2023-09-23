package ovh.major.mybackendapp.domain.login.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;

@Builder
public record UserRequestDTO(

        @NotBlank(message = "Username must be not blank!")
        String name,

        @NotBlank(message = "password must be not blank!")
        String password
) {
}
