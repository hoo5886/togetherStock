package com.stock.togetherStock.post;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.http.RequestEntity.post;

import com.stock.togetherStock.comment.repository.CommentRepository;
import com.stock.togetherStock.member.domain.Member;
import com.stock.togetherStock.member.domain.MemberDto;
import com.stock.togetherStock.member.repository.MemberRepository;
import com.stock.togetherStock.post.domain.Post;
import com.stock.togetherStock.post.domain.PostDto;
import com.stock.togetherStock.post.repository.PostRepository;
import com.stock.togetherStock.post.service.PostService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private PostService postService;


    protected PostDto createPostDto() {
        return PostDto.builder()
            .title("제목1")
            .content("내용1")
            .regiPostDate(LocalDateTime.now())
            .build();
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

//    @Test
//    @DisplayName("특정 게시물 조회")
//    public void read() {
//        //given
//        PostDto postDto = createPostDto();
//        Post post = postDto.toEntity();
//        List<Comment> commentList = new ArrayList<>();
//
//        given(postRepository.save(any())).willReturn(post);
//        given(commentRepository.findCommentsByPost(post)).willReturn(commentList);
//
//        //when
//
//
//        //then
//        verify(commentRepository).findCommentsByPost(post);
//
//    }

    @Test
    @DisplayName("게시물 등록")
    public void write() {
        //given
        PostDto postDto = createPostDto();
        Post post = postDto.toEntity();
        given(postRepository.save(any())).willReturn(post);

        MemberDto memberDto = createMemberDto();
        Member member = memberDto.toEntity();

        //when
        Post savedPost = postService.write(postDto, member);

        //then
        assertEquals(post.getTitle(), savedPost.getTitle());
        assertEquals(post.getContent(), savedPost.getContent());
    }

    @Test
    @DisplayName("게시물 목록 조회")
    public void list() {
        //given
        PostDto postDto = createPostDto();
        Post post = postDto.toEntity();
        List<Post> postList = new ArrayList<>();
        postList.add(post);

        given(postRepository.findAll()).willReturn(postList);

        //when
        List<Post> savedPostList = postService.postList();

        //then
        assertThat(savedPostList).isEqualTo(postList);
    }

    @Test
    @DisplayName("게시물 정보 수정 성공")
    void update() {
        //given
        PostDto postDto = createPostDto();
        Post post = postDto.toEntity();

        PostDto newPostDto = PostDto.builder()
            .title("newTitle")
            .content("newContent")
            .build();

        //when
        given(postRepository.findByPostId(1L))
            .willReturn(Optional.of(post));
        postService.PostUpdate(1L, newPostDto);

        //then
        assertEquals("newTitle", post.getTitle());
        assertEquals("newContent", post.getContent());
    }

    @Test
    @DisplayName("게시물 정보 삭제 성공")
    void delete() {
        doNothing().when(postRepository).deleteById(anyLong());

        assertThat(postService.delete(1L)).isEqualTo(true);
    }

}