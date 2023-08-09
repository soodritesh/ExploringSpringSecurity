package com.Authentication.ipAddress.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.util.function.Supplier;

@Configuration
public class ProjectConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/private/**").access(new AuthorizationManager<RequestAuthorizationContext>() {
                    @Override
                    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
                        String userIp = object.getRequest().getRemoteAddr();
                        if (userIp.equals("0:0:0:0:0:0:0:1")) return new AuthorizationDecision(true);
                        else return new AuthorizationDecision(false);
                    }
                })
                .requestMatchers("/public/**").permitAll()
                .anyRequest().denyAll()
                .and()
                .formLogin()
                .and()
                .httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
