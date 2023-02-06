package com.stock.togetherStock.security;

import com.stock.togetherStock.member.repository.MemberRepository;
import com.stock.togetherStock.security.handler.AuthFailureHandler;
import com.stock.togetherStock.security.handler.AuthSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthSuccessHandler authSuccessHandler;
    private final AuthFailureHandler failureHandler;

    @Bean
    AuthenticationManager authenticationManager(
        AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            //.antMatchers("/post/{postId}/comment/write").authenticated()
            .antMatchers("/**").permitAll();

        http.formLogin()
            .usernameParameter("email")
            .passwordParameter("password")
            .loginPage("/")
            .loginProcessingUrl("/loginProc")
            .successHandler(authSuccessHandler)
            .failureHandler(failureHandler);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}