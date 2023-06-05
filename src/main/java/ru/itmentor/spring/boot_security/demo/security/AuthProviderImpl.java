package ru.itmentor.spring.boot_security.demo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.itmentor.spring.boot_security.demo.services.UserService;
import ru.itmentor.spring.boot_security.demo.model.User;


@Component
@RequiredArgsConstructor
public class AuthProviderImpl implements AuthenticationProvider {
    private final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        UserDetails userdetails = userService.loadUserByUsername(username);
        String password = authentication.getCredentials().toString();
        if (!password.equals(userdetails.getPassword())) {
            throw new BadCredentialsException("Incorrect password !");
        }
        return new UsernamePasswordAuthenticationToken(userdetails, password, ((User) userdetails).getRoleList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}