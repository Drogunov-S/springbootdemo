package ru.drogunov.springbootdemo.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.drogunov.springbootdemo.services.PersonDerailsService;

import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {
    private final PersonDerailsService personDerailsService;
    
    public AuthProviderImpl(PersonDerailsService peopleService) {
        this.personDerailsService = peopleService;
    }
    
    /**
     * Проверка пароля из формы и в БД
     *
     * @return токен с {@link PersonDetails}
     * и листом прав {@link org.springframework.security.core.GrantedAuthority}
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        UserDetails userDetails = personDerailsService.loadUserByUsername(login);
        String password = authentication.getCredentials().toString();
        if (!password.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect password!");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, password, Collections.emptyList());
    }
    
    /**
     * Проверка принадлежности провайдера к сценарию
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
