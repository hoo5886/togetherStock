package com.stock.togetherStock.member.controller;

import com.stock.togetherStock.common.response.ErrorResponse;
import com.stock.togetherStock.member.domain.MemberDto;
import com.stock.togetherStock.member.exception.MemberException;
import com.stock.togetherStock.member.service.MemberService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/signup")
    public String signup(Model model) {
        model.addAttribute("memberDto", new MemberDto());

        return "/member/singUpForm";
    }

    @PostMapping("/member/signup")
    public String signupProc(@Valid MemberDto memberDto, Model model) throws Exception {
        memberService.signUp(memberDto);

        return "redirect:/";
    }
}
