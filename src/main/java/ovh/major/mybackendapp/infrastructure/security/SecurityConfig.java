package ovh.major.mybackendapp.infrastructure.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ovh.major.mybackendapp.domain.login.LoginFacade;
import ovh.major.mybackendapp.infrastructure.security.jwt.JwtAuthTokenFilter;


@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final JwtAuthTokenFilter jwtAuthTokenFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(LoginFacade loginFacade) {
        return new LoginUserDetailsService(loginFacade);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.authorizeHttpRequests()
                .requestMatchers(
//                        "/swagger-ui/**",
//                        "/swagger-ui.html",
                        "/api/auth/**",
//                        "/v3/api-docs.yaml",
//                        "/v3/api-docs/**",
//                        "/v3/api-docs",
//                        "/v3/api-docs/swagger-config",
                        "/webjars/**",
                        "/login/**"
//                        "/swagger-resources/**"
                ).permitAll()
                .requestMatchers(HttpMethod.GET,
                        "/posts"
                        ).permitAll()
                .anyRequest().authenticated()
                .and().headers().frameOptions().disable()
                .and().httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling()
                .and().addFilterBefore(jwtAuthTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

}
