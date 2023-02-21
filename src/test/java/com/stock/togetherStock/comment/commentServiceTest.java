package com.stock.togetherStock.comment;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.stock.togetherStock.comment.domain.Comment;
import com.stock.togetherStock.comment.domain.CommentDto;
import com.stock.togetherStock.comment.repository.CommentRepository;
import com.stock.togetherStock.comment.service.CommentService;
import com.stock.togetherStock.member.domain.MemberDto;
import com.stock.togetherStock.member.repository.MemberRepository;
import com.stock.togetherStock.post.domain.PostDto;
import com.stock.togetherStock.post.repository.PostRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
@Transactional
@ExtendWith(MockitoExtension.class)
public class commentServiceTest {

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
    @DisplayName("댓글 수정 성공")
    @WithMockUser(username = "test@test.com", password="1234", roles="USER")
    @Transactional
    public void update() {

        //given
        PostDto postDto = createPostDto();
        MemberDto memberDto = createMemberDto();
        CommentDto commentDto = createCommentDto();
        commentDto.setPostDto(postDto);
        commentDto.setMemberDto(memberDto);
        Comment comment = commentDto.toEntity();
        given(commentRepository.findByCommentId(1L))
            .willReturn(Optional.of(comment));

        //commentDto.getCommentContent = "테스트용 코멘트"
        commentService.write(postDto, memberDto, commentDto);

        //when
        commentDto.setCommentContent("수정 댓글");
        commentService.commentUpdate(commentDto.getCommentId(), commentDto);

        //then
        Assertions.assertEquals("수정 댓글", commentDto.getCommentContent());
        verify(commentRepository).findByCommentId(1L);
    }
}
