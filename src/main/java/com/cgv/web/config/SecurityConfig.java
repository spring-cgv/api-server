package com.cgv.web.config;

import com.cgv.web.config.security.JsonUsernamePasswordAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter;

    private static final String[] GET_PERMIT_ALL_MATCHERS = { "/movies/*", "/movies/*/tickets/distribution" };
    private static final String[] POST_PERMIT_ALL_MATCHERS = { "/users" };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable();

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, GET_PERMIT_ALL_MATCHERS).permitAll()
                .antMatchers(HttpMethod.POST, POST_PERMIT_ALL_MATCHERS).permitAll()
                .anyRequest().authenticated();

        http
                .formLogin().disable();

        http
                .addFilterAt(jsonUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/session", "DELETE"))
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.NO_CONTENT));

        http
                .httpBasic();

        http
                .headers()
                .frameOptions()
                .sameOrigin();

        return http.build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
