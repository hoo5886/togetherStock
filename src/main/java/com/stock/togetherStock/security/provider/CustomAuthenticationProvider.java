/*
package com.stock.togetherStock.security.provider;

import com.stock.togetherStock.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService MemberService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication)
        throws AuthenticationException {

        String email = authentication.getName();
        String password = (String) authentication.getCredentials();

        Member member = (Member) MemberService.loadUserByUsername(email);

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        UsernamePasswordAuthenticationToken token =
            new UsernamePasswordAuthenticationToken(member, null, null);

        return token;
    }

    public boolean supports(Class<?> authentication) {
        return false;
    }
}
*/
