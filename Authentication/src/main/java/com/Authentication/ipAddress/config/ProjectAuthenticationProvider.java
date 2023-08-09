package com.Authentication.ipAddress.config;

import com.Authentication.ipAddress.entity.User;
import com.Authentication.ipAddress.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository uRepo;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();

        String password = authentication.getCredentials().toString();

        User user = uRepo.findByEmail(username);

        if (user != null){
            if (user.getPassword().equals(password)){
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("USER"));

                return new UsernamePasswordAuthenticationToken(username, password, authorities);
            }
            else throw new BadCredentialsException("Invalid Password");
        }
        else throw  new BadCredentialsException("No user found");

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
