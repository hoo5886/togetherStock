package com.stock.togetherStock.post.controller;

import com.stock.togetherStock.comment.domain.Comment;
import com.stock.togetherStock.comment.domain.CommentDto;
import com.stock.togetherStock.comment.repository.CommentRepository;
import com.stock.togetherStock.member.domain.Member;
import com.stock.togetherStock.member.repository.MemberRepository;
import com.stock.togetherStock.post.domain.Post;
import com.stock.togetherStock.post.domain.PostDto;
import com.stock.togetherStock.post.exception.PostErrorCode;
import com.stock.togetherStock.post.exception.PostException;
import com.stock.togetherStock.post.repository.PostRepository;
import com.stock.togetherStock.post.service.PostService;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;


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
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/post/write")
    public String writeProc(PostDto postDto, Principal principal) throws PostException {

        if (principal == null) {
            //익명사용자의 게시글 작성
            throw new PostException(PostErrorCode.POST_NEED_LOGIN);
        } else {
            Member member = memberRepository.findByEmail(principal.getName()).get();
            postService.write(postDto, member);
            return "redirect:/post/list";
        }
    }

    /**
     * 특정 게시물 조회 + 댓글 조회
     */
    @GetMapping("/post/{postId}")
    public String view(Model model, @PathVariable Long postId, Principal principal) {

        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new PostException(PostErrorCode.POST_NO_POST));

        if (!commentRepository.findAllByPostPostId(postId).isEmpty()) {
            List<Comment> commentList = commentRepository.findAllByPostPostId(postId);
            model.addAttribute("commentList", commentList);
        }

        //댓글 작성할 때 필요한 Dto
        model.addAttribute("commentForWrite", new CommentDto());
        model.addAttribute("post", post);
        model.addAttribute("principal", principal);

        return "/post/postView";
    }

    /**
     * 게시물 수정
     */
    @GetMapping("/post/edit/{postId}")
    public String updatePostPage(Model model, @PathVariable Long postId){

        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new PostException(PostErrorCode.POST_NO_POST));

        model.addAttribute("post", post.toPostDto());

        return "post/postUpdateForm";
    }

    /**
     * 게시물 수정 입력
     */
    @PostMapping("/post/edit/{postId}")
    public String updatePostPageProc(@PathVariable Long postId, PostDto postDto) {

        postService.postUpdate(postId, postDto);

        return "redirect:/post/list";
    }

    /**
     * 게시물 삭제
     */
    //localhost:8080/post/delete
    @GetMapping("/post/delete/{postId}")
    public String delete(@PathVariable Long postId) {

        if (postRepository.findByPostId(postId).isPresent()) {
            postService.delete(postId);
            return "redirect:/post/list";
        } else {
            throw new PostException(PostErrorCode.POST_NO_POST);
        }
    }
}
