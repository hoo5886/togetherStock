package com.stock.togetherStock.security.handler;

import com.stock.togetherStock.member.repository.MemberRepository;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final MemberRepository memberRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
        HttpServletResponse response, Authentication authentication)
        throws IOException, ServletException
    {
        setDefaultTargetUrl("/login");

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
