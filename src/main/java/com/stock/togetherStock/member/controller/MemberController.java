package com.stock.togetherStock.member.controller;

import com.stock.togetherStock.member.domain.Member;
import com.stock.togetherStock.member.domain.MemberDto;
import com.stock.togetherStock.member.exception.MemberErrorCode;
import com.stock.togetherStock.member.exception.MemberException;
import com.stock.togetherStock.member.repository.MemberRepository;
import com.stock.togetherStock.member.service.MemberService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    /**
     * 회원가입 페이지
     */
    @GetMapping("/member/signup")
    public String signup(Model model) {
        model.addAttribute("memberDto", new MemberDto());

        return "/member/singUpForm";
    }

    /**
     * 회원가입 입력
     */
    @PostMapping("/member/signup")
    public String signupProc(@Valid MemberDto memberDto) throws Exception {
        memberService.signUp(memberDto);

        return "redirect:/";
    }

    /**
     * 회원 마이 페이지
     */
    // localhost:8080/member/detail?id=1
    @GetMapping("/member/detail")
    public String detail(Model model, Long id) {

        model.addAttribute("member", memberService.detail(id));

        return "/member/detail";
    }

    /**
     * 회원정보 수정 페이지
     */
    @GetMapping("/member/edit/{id}")
    public String updatePage(Model model, @PathVariable Long id) throws Exception {

        Member member = memberRepository.findByMemberId(id)
            .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        model.addAttribute("member", member.toMemberDto());

        return "member/updateForm";
    }

    /**
     * 회원정보 수정 입력
     */
    @PostMapping("/member/edit/{id}")
    public String updateProc(@PathVariable Long id, MemberDto memberDto) {

        memberService.update(id, memberDto);

        return "redirect:/";
    }

    /**
     * 회원정보 삭제
     */
    @DeleteMapping("/member/delete")
    public String deletePage(Long id) {

        memberService.delete(id);

        return "redirect:/post/postList";
    }

}
