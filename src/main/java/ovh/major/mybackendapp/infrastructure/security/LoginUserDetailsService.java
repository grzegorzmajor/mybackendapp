package ovh.major.mybackendapp.infrastructure.security;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ovh.major.mybackendapp.domain.login.LoginFacade;
import ovh.major.mybackendapp.domain.login.dto.SingleUserDTO;

import java.util.Collections;

@AllArgsConstructor
class LoginUserDetailsService implements UserDetailsService {

    private final LoginFacade loginFacade;

    @Override
    public UserDetails loadUserByUsername(String username) throws BadCredentialsException {
        SingleUserDTO singleUserDTO = loginFacade.findByName(username);
        return getUser(singleUserDTO);
    }

    private org.springframework.security.core.userdetails.User getUser(SingleUserDTO user) {
        return new org.springframework.security.core.userdetails.User(
                user.name(),
                user.password(),
                Collections.emptyList());
    }
}
