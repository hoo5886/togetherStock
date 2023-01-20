package com.stock.togetherStock.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.stock.togetherStock.member.domain.Member;
import com.stock.togetherStock.member.domain.MemberDto;
import com.stock.togetherStock.member.exception.MemberErrorCode;
import com.stock.togetherStock.member.exception.MemberException;
import com.stock.togetherStock.member.repository.MemberRepository;
import com.stock.togetherStock.member.service.MemberService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(MockitoExtension.class)
@Transactional
class MemberServiceTest {
    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        memberService = new MemberService(memberRepository, passwordEncoder);
    }

    @Test
    @DisplayName("회원가입 테스트")
    void createMember() throws Exception {
        //given
        MemberDto memberDto = MemberDto.builder()
            .email("test@naver.com")
            .password("12345")
            .name("test")
            .nickname("hateTest")
            .phone("11111111")
            .build();
        Member member = memberDto.toEntity();

        //when
        given(memberRepository.save(any()))
            .willReturn(member);

        Member savedMember = memberService.signUp(memberDto);

        //then
        assertEquals(member.getEmail(), savedMember.getEmail());
        assertEquals(member.getName(), savedMember.getName());
        assertEquals(member.getNickname(), savedMember.getNickname());
        assertEquals(member.getPhone(), savedMember.getPhone());
        assertEquals(member.getPassword(), savedMember.getPassword());
    }

    @Test
    @DisplayName("회원가입 중복 테스트")
    void duplicateMember() {
        //given
        MemberDto memberDto = MemberDto.builder()
            .email("test@naver.com")
            .password("12345")
            .name("test")
            .nickname("hateTest")
            .phone("11111111")
            .build();

        given(memberRepository.findByEmail(anyString()))
            .willReturn(Optional.of(Member.builder().build()));

        //when
        MemberException memberException = assertThrows(MemberException.class,
            () -> memberService.signUp(memberDto));

        // then
        assertThat(memberException.getErrorCode()).isEqualTo(
            MemberErrorCode.ALREADY_EXIST_EMAIL.toString());

        assertThat(memberException.getErrorMessage()).isEqualTo(
            MemberErrorCode.ALREADY_EXIST_EMAIL.getMessage());
    }

    @Test
    @DisplayName("회원정보 수정 테스트")
    void update() {
        //given
        MemberDto memberDto = MemberDto.builder()
            .email("test@naver.com")
            .password("12345")
            .name("test")
            .nickname("hateTest")
            .phone("11111111")
            .build();

        given(memberRepository.save(memberDto.toEntity()))
            .willReturn();

        assertThat()

    }
}

