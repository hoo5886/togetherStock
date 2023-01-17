package com.stock.togetherStock.member.controller;

import com.stock.togetherStock.member.domain.MemberDto;
import com.stock.togetherStock.member.service.MemberService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    public String signupProc(@Valid MemberDto memberDto) throws Exception {
        memberService.signUp(memberDto);

        return "redirect:/";
    }

    @GetMapping("/member/detail")
    public String detail(Model model, Long id) {

        model.addAttribute("member", memberService.detail(id));

        return "/member/detail";
    }

    @GetMapping("/member/edit/{id}")
    public String update(@PathVariable Long id) {

        return "member/updateForm";
    }

}
