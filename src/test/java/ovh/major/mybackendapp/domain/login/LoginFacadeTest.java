package ovh.major.mybackendapp.domain.login;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class LoginFacadeTest {

    private final SingleUser singleUserForTests =  new SingleUser("user","$2a$12$PVKK8k5bWlgmUlpWQGTGPeNZNbRYCyYR3pn/uFBVTLvEWLL7AeWf2"); //encrypted "pass"
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final LoginFacade loginFacade = new LoginFacade(singleUserForTests,passwordEncoder);


    @Test
    public void shouldReturnUserResponseDTOWithValueIsLoggedEqualFalseWhenPasswordIsWrong() {
        //given
        UserRequestDTO userRequestDTO = UserRequestDTO.builder()
                .name("user")
                .password("wrong password")
                .build();

        //when
        UserResponseDTO userResponseDTO = loginFacade.authenticateUser(userRequestDTO);

        //then
        assertAll(
                () -> assertThat(userResponseDTO.name(),is(equalTo(userRequestDTO.name()))),
                () -> assertFalse(userResponseDTO.isLogged())
        );

    }
    @Test
    public void shouldReturnUserResponseDTOWithValueIsLoggedEqualFalseWhenNameIsWrong() {
        //given
        UserRequestDTO userRequestDTO = UserRequestDTO.builder()
                .name("wrong user")
                .password("pass")
                .build();

        //when
        UserResponseDTO userResponseDTO = loginFacade.authenticateUser(userRequestDTO);

        //then
        assertAll(
                () -> assertThat(userResponseDTO.name(),is(equalTo(userRequestDTO.name()))),
                () -> assertFalse(userResponseDTO.isLogged())
        );

    }
    @Test
    public void shouldReturnUserResponseDTOWithValueIsLoggedEqualTrueWhenNameAndPasswordIsCorrect() {
        //given
        UserRequestDTO userRequestDTO = UserRequestDTO.builder()
                .name("user")
                .password("pass")
                .build();

        //when
        UserResponseDTO userResponseDTO = loginFacade.authenticateUser(userRequestDTO);

        //then
        assertAll(
                () -> assertThat(singleUserForTests.hashedPassword(), is(equalTo("$2a$12$PVKK8k5bWlgmUlpWQGTGPeNZNbRYCyYR3pn/uFBVTLvEWLL7AeWf2"))),
                () -> assertThat(userResponseDTO.name(),is(equalTo(userRequestDTO.name()))),
                () -> assertTrue(userResponseDTO.isLogged())
        );

    }


}