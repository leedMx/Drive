package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Model.UserModel;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService implements AuthenticationProvider {
    private UserService userService;
    private HashService hash;

    public AuthenticationService(UserService userService, HashService hash) {
        this.userService = userService;
        this.hash = hash;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        System.out.println(authentication);
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserModel user = userService.getUser(username);
        if (user != null)
            if (passwordIsCorrect(password, user))
                return token(user);
        return null;
    }

    private Authentication token(UserModel user) {
        return new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    private boolean passwordIsCorrect(String password, UserModel user) {
        return user.getPassword().equals(
                hash.getHashedValue(password, user.getSalt()));
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
