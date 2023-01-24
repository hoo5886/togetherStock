package com.stock.togetherStock.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import org.springframework.security.crypto.password.PasswordEncoder;
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

    protected MemberDto createMemberDto() {

        return MemberDto.builder()
            .email("test@naver.com")
            .password("12345")
            .name("test")
            .nickname("hateTest")
            .phone("11111111")
            .build();
    }

    @Test
    @DisplayName("회원가입 테스트")
    void createMember() throws Exception {
        //given
        MemberDto memberDto = createMemberDto();
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
        MemberDto memberDto = createMemberDto();

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
    @DisplayName("회원정보 수정 성공")
    void update() {
        //given
        MemberDto memberDto = createMemberDto();
        Member member = memberDto.toEntity();

        MemberDto newMemberDto = MemberDto.builder()
            .memberId(1L)
            .email("test@naver.com")
            .password("12345")
            .name("newName")
            .nickname("newNick")
            .phone("22222222")
            .intro("hello")
            .build();

        //when
        given(memberRepository.findById(1L))
            .willReturn(Optional.of(member));
        memberService.update(1L, newMemberDto);

        //then
        assertEquals("newName", member.getName());
        assertEquals("newNick", member.getNickname());
        assertEquals("22222222", member.getPhone());
        assertEquals("hello", member.getIntro());
    }

    @Test
    @DisplayName("회원정보 삭제 성공")
    void delete() {
        doNothing().when(memberRepository).deleteById(anyLong());

        assertThat(memberService.delete(1L)).isEqualTo(true);
    }
}

