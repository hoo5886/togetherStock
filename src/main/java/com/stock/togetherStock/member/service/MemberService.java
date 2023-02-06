package com.stock.togetherStock.member.service;

import com.stock.togetherStock.member.domain.Member;
import com.stock.togetherStock.member.domain.MemberDto;
import com.stock.togetherStock.member.exception.MemberErrorCode;
import com.stock.togetherStock.member.exception.MemberException;
import com.stock.togetherStock.member.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     * */
    @Transactional
    public Member signUp(MemberDto memberDto) throws Exception {

        if (memberRepository.findByEmail(memberDto.getEmail()).isPresent()) {
            throw new MemberException(MemberErrorCode.ALREADY_EXIST_EMAIL);
        }

        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        return memberRepository.save(memberDto.toEntity());
    }

    /**
     * 마이 페이지
     * */
    public Member detail(Long id) {

        return memberRepository.findByMemberId(id)
            .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
    }

    /**
     * 회원정보 수정
     */
    @Transactional
    public Long update(Long id, MemberDto memberDto) {

        Member member = memberRepository.findById(id)
            .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        member.update(
            memberDto.getName(),
            memberDto.getNickname(),
            memberDto.getPhone(),
            memberDto.getIntro()
        );

        return id;
    }

    /**
     * 회원정보 삭제
     */
    @Transactional
    public boolean delete(Long id) {

        memberRepository.deleteById(id);

        return true;
    }

    /**
     * member 정보 가져오기
     */
    public Member getMember(String username) {
        //findByEmail 또는 findBy
        Optional<Member> member = memberRepository.findByEmail(username);
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
        }

    }
}
