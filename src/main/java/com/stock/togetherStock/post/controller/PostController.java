package com.stock.togetherStock.post.controller;

import com.stock.togetherStock.post.domain.Post;
import com.stock.togetherStock.post.domain.PostDto;
import com.stock.togetherStock.post.exception.PostErrorCode;
import com.stock.togetherStock.post.exception.PostException;
import com.stock.togetherStock.post.repository.PostRepository;
import com.stock.togetherStock.post.service.PostService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;


    /**
     * 게시물 목록
     */
    @GetMapping("/post/list")
    public String list(Model model) {
        model.addAttribute("postList", postService.postList());

        return "/post/postList";
    }

    /**
     * 게시물 작성
     */
    @GetMapping("/post/write")
    public String write(Model model) {

        model.addAttribute("form", new PostDto());

        return "/post/postRegiForm";
    }

    /**
     * 게시물 입력
     */
    @PostMapping("/post/write")
    public String writeProc(PostDto postDto) {

        postService.write(postDto);

        return "redirect:/post/list";
    }

    /**
     * 특정 게시물 조회
     */
    @GetMapping("/post/{postId}")
    public String view(Model model, @PathVariable Long postId) {

        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new PostException(PostErrorCode.POST_ERROR_CODE));

        model.addAttribute("post", post);

        return "/post/postView";
    }

    /**
     * 게시물 수정
     */
    @GetMapping("/post/edit/{postId}")
    public String updatePostPage(Model model, @PathVariable Long postId){

        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new PostException(PostErrorCode.POST_ERROR_CODE));

        model.addAttribute("post", post.toPostDto());

        return "post/postUpdateForm";
    }

    /**
     * 게시물 수정 입력
     */
    @PostMapping("/post/edit/{postId}")
    public String updatePostPageProc(@PathVariable Long postId, PostDto postDto) {

        postService.PostUpdate(postId, postDto);

        return "redirect:/post/list";
    }

    /**
     * 게시물 삭제
     */
    //localhost:8080/post/delete
    @DeleteMapping("/post/delete")
    public String delete(Long postId) {

        if (postRepository.findByPostId(postId).isPresent()) {
            postService.delete(postId);
        } else {
            throw new PostException(PostErrorCode.POST_ERROR_CODE);
        }

        return "redirect:/post/list";
    }
}
