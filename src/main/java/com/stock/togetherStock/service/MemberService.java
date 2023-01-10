package com.stock.togetherStock.service;

import com.stock.togetherStock.model.Dto.MemberDto;
import com.stock.togetherStock.model.Member;
import com.stock.togetherStock.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member create(MemberDto memberDto) {

        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        Member member = memberDto.toEntity();

        return memberRepository.save(member);
    }

}
