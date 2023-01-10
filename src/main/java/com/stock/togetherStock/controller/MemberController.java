package com.stock.togetherStock.controller;

import com.stock.togetherStock.model.Dto.MemberDto;
import com.stock.togetherStock.service.MemberService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("memberDto", new MemberDto());

        return "member/create";
    }

    @PostMapping("/create")
    public String createProc(@Valid MemberDto memberDto) {

        memberService.create(memberDto);

        return "login";
    }
}
