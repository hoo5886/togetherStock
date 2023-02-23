package com.stock.togetherStock.comment.controller;

import com.stock.togetherStock.comment.domain.Comment;
import com.stock.togetherStock.comment.domain.CommentDto;
import com.stock.togetherStock.comment.exception.CommentErrorCode;
import com.stock.togetherStock.comment.exception.CommentException;
import com.stock.togetherStock.comment.repository.CommentRepository;
import com.stock.togetherStock.comment.service.CommentService;
import com.stock.togetherStock.member.domain.Member;
import com.stock.togetherStock.member.domain.MemberDto;
import com.stock.togetherStock.member.exception.MemberErrorCode;
import com.stock.togetherStock.member.exception.MemberException;
import com.stock.togetherStock.member.service.MemberService;
import com.stock.togetherStock.post.domain.Post;
import com.stock.togetherStock.post.domain.PostDto;
import com.stock.togetherStock.post.exception.PostErrorCode;
import com.stock.togetherStock.post.exception.PostException;
import com.stock.togetherStock.post.repository.PostRepository;
import com.stock.togetherStock.post.service.PostService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.naming.Binding;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final MemberService memberService;
    private final PostService postService;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    /**
     * 댓글 작성 입력
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/post/{postId}/comment/write")
    String commentWriteProc(@PathVariable Long postId,
                            CommentDto commentDto,
                            Principal principal) throws MemberException, PostException {
        if (principal == null) {
            // 익명 사용자인 경우, 예외처리
            throw new MemberException(MemberErrorCode.MEMBER_NEED_LOGIN);
        } else if (postId == null) {
            // 게시글이 존재하지 않는 경우, 예외처리
            throw new PostException(PostErrorCode.POST_NO_POST);
        } else {
            PostDto postDto = postService.getPost(postId).toPostDto();
            MemberDto memberDto = memberService.getMember(principal.getName()).toMemberDto();

            commentService.write(postDto, memberDto, commentDto);
        }

        return "redirect:/post/{postId}";
    }

    /**
     * 댓글 수정 페이지
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/comment/edit/{commentId}")
    String commentUpdate(@PathVariable Long commentId,
                         Principal principal,
                         Model model) throws MemberException, PostException
    {
        Post post = commentRepository.findByCommentId(commentId).get().getPost();

        if (principal == null) {
            // 익명 사용자인 경우, 예외처리
            throw new MemberException(MemberErrorCode.MEMBER_NEED_LOGIN);
        } else if (post == null) {
            // 게시글이 존재하지 않는 경우, 예외처리
            throw new PostException(PostErrorCode.POST_NO_POST);
        } else {

            //comment 도메인리스트를 dto로 변환해주는 작업
            List<Comment> comments = commentRepository.findAllByPostPostId(post.getPostId());
            List<CommentDto> commentDtoes = new ArrayList<>();
            for (Comment comment : comments) {
                CommentDto commentDto = comment.toDto();
                commentDtoes.add(commentDto);
            }

            //뷰페이지로 넘긴다.
            model.addAttribute("post", commentRepository.findByCommentId(commentId).get().getPost());
            model.addAttribute("commentDtoes", commentDtoes);
            model.addAttribute("commentForUpdate", commentRepository.findByCommentId(commentId).get().toDto());
            model.addAttribute("principal", principal);
            return "comment/commentUpdateForm";
        }
    }

    /**
     * 댓글 수정 입력
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/comment/edit/{commentId}")
    String commentUpdateProc(@PathVariable Long commentId,
                            @Valid CommentDto commentDto,
                            BindingResult bindingResult,
                            Principal principal)
        throws MemberException, PostException {

        Long postId = commentRepository.findByCommentId(commentId).get().getPost().getPostId();

        if (bindingResult.hasErrors()) {
            //System.out.printf("redirect:/post/$s/comment", postId);
            return "redirect:/post/" + postId;
        }
        // view에서 commentId를 받아와서 comment 객체에 저장.
        Optional<Comment> comment = commentRepository.findByCommentId(commentId);
        if (comment.isPresent()) {
            Comment c = comment.get();
            if (!c.getMember().getEmail().equals(principal.getName())) {
                // 댓글 작성자와 댓글 수정을 시도하는 사람이 다른 경우, 예외 발생
                throw new MemberException(MemberErrorCode.MEMBER_NOT_MATCH);
            }
            commentService.commentUpdate(c.getCommentId(), commentDto);
        } else {
            throw new CommentException(CommentErrorCode.COMMENT_NOT_FOUND);
        }

        return "redirect:/post/" + postId;
    }

    /**
     * 댓글 삭제
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/comment/delete/{commentId}")
    public String delete(@PathVariable Long commentId) {

        Optional<Comment> comment = commentRepository.findByCommentId(commentId);

        if (comment.isPresent()) {
            Long postId = comment.get().getPost().getPostId();
            commentService.delete(commentId);

            return "redirect:/post/" + postId;
        } else {
            throw new CommentException(CommentErrorCode.COMMENT_NOT_FOUND);
        }
    }
}
