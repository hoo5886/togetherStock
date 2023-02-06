package com.stock.togetherStock.config;

import com.stock.togetherStock.member.domain.MemberDto;
import com.stock.togetherStock.member.service.MemberService;
import com.stock.togetherStock.security.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

@RequiredArgsConstructor
public class WithAccountSecurityContextFactory implements WithSecurityContextFactory<WithAccount> {

    private final CustomUserDetailsService customUserDetailsService;

    private final MemberService memberService;

    @Override
    public SecurityContext createSecurityContext(WithAccount withAccount) {
        String email = withAccount.value();

        MemberDto memberDto = MemberDto.builder()
            .email(email)
            .password("1234")
            .build();
        try {
            memberService.signUp(memberDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        UserDetails principal = customUserDetailsService.loadUserByUsername(email);
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal
            , principal.getPassword()
            , principal.getAuthorities());
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);

        return securityContext;
    }
}