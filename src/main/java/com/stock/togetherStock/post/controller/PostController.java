package com.stock.togetherStock.post.controller;

import com.stock.togetherStock.post.domain.PostDto;
import com.stock.togetherStock.post.repository.PostRepository;
import com.stock.togetherStock.post.service.PostService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

        return "redirect:/post/postList";
    }

    /**
     * 특정 게시물 조회
     */
//    @GetMapping("/post/{postId}")
//    public String view(Model model, @PathVariable Long postId) {
//
//        Post post = postRepository.findByPostId(postId)
//                .orElseThrow(() -> new PostException(PostErrorCode.POST_ERROR_CODE));
//
//        model.addAttribute("view", post.);
//
//        return "/post/postView";
//
//    }
}
