package com.stock.togetherStock.post;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.stock.togetherStock.post.domain.Post;
import com.stock.togetherStock.post.domain.PostDto;
import com.stock.togetherStock.post.repository.PostRepository;
import com.stock.togetherStock.post.service.PostService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    protected PostDto createPostDto() {
        return PostDto.builder()
            .title("제목1")
            .content("내용1")
            .regiPostDate(LocalDateTime.now())
            .build();
    }

    @Test
    @DisplayName("게시물 등록")
    public void write() {
        //given
        PostDto postDto = createPostDto();
        Post post = postDto.toEntity();
        given(postRepository.save(any())).willReturn(post);

        //when
        Post savedPost = postService.write(postDto);

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

}