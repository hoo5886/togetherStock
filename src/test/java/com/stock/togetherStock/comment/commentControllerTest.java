package com.stock.togetherStock.comment;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.stock.togetherStock.comment.domain.CommentDto;
import com.stock.togetherStock.comment.repository.CommentRepository;
import com.stock.togetherStock.comment.service.CommentService;
import com.stock.togetherStock.member.domain.MemberDto;
import com.stock.togetherStock.member.exception.MemberErrorCode;
import com.stock.togetherStock.member.exception.MemberException;
import com.stock.togetherStock.member.repository.MemberRepository;
import com.stock.togetherStock.post.domain.PostDto;
import com.stock.togetherStock.post.repository.PostRepository;
import java.time.LocalDateTime;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class commentControllerTest {

    @Autowired
    MockMvc mvc;

    @Mock
    PostRepository postRepository;

    @Mock
    MemberRepository memberRepository;

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    CommentService commentService;

    @BeforeEach
    void setUp() {
        commentService = new CommentService(commentRepository, postRepository, memberRepository);
    }

    protected MemberDto createMemberDto() {
        return MemberDto.builder()
            .email("test@naver.com")
            .password("12345")
            .name("test")
            .nickname("hateTest")
            .phone("11111111")
            .memberId(1L)
            .build();
    }

    protected PostDto createPostDto() {
        return PostDto.builder()
            .title("제목1")
            .content("내용1")
            .regiPostDate(LocalDateTime.now())
            .postId(1L)
            .build();
    }

    protected CommentDto createCommentDto() {
        return CommentDto.builder()
            .commentId(1L)
            .commentContent("테스트용 코멘트")
            .build();
    }

    @Test
    @DisplayName("인증 후 댓글 작성 성공")
    @WithMockUser(username = "test@test.com", password="1234", roles="USER")
    @Transactional
    public void Post() throws Exception {

        String json = new Gson().toJson(createCommentDto());

        mvc.perform(post("/post/4/comment/write")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isFound())
            .andExpect(redirectedUrl("/post/4"))
            .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("익명 사용자 댓글 작성 실패")
    @WithAnonymousUser
    public void Post_failure() throws Exception {

        mvc.perform(
            post("/post/4/comment/write")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(MemberErrorCode.MEMBER_NEED_LOGIN)))
            .andExpect(
                (result) -> Assert.assertTrue(result.getResolvedException().getClass().isAssignableFrom(MemberException.class))
            )
            .andExpect(status().is2xxSuccessful());
    }


}
