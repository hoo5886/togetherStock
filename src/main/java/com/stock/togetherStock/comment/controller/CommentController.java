package com.stock.togetherStock.comment.controller;

import com.stock.togetherStock.comment.domain.CommentDto;
import com.stock.togetherStock.comment.service.CommentService;
import com.stock.togetherStock.member.domain.Member;
import com.stock.togetherStock.member.exception.MemberErrorCode;
import com.stock.togetherStock.member.exception.MemberException;
import com.stock.togetherStock.member.service.MemberService;
import com.stock.togetherStock.post.domain.Post;
import com.stock.togetherStock.post.exception.PostErrorCode;
import com.stock.togetherStock.post.exception.PostException;
import com.stock.togetherStock.post.service.PostService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final MemberService memberService;
    private final PostService postService;

    /**
     * 댓글 작성 입력
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/post/{postId}/comment/write")
    public String commentWriteProc(@PathVariable Long postId, CommentDto commentDto,
                                    Principal principal) throws MemberException, PostException {

        if (principal == null) {
            // 익명 사용자인 경우, 예외처리
            throw new MemberException(MemberErrorCode.MEMBER_NEED_LOGIN);
        } else if (postId == null){
            // 게시글이 존재하지 않는 경우, 예외처리
            throw new PostException(PostErrorCode.POST_NO_POST);
        } else {
            Post post = postService.getPost(postId);
            Member member = memberService.getMember(principal.getName());

            commentService.write(post, member, commentDto);
        }

        return "/post/postView";
    }
}
