package com.stock.togetherStock.comment.service;

import com.stock.togetherStock.comment.domain.Comment;
import com.stock.togetherStock.comment.domain.CommentDto;
import com.stock.togetherStock.comment.repository.CommentRepository;
import com.stock.togetherStock.member.domain.Member;
import com.stock.togetherStock.member.domain.MemberDto;
import com.stock.togetherStock.member.repository.MemberRepository;
import com.stock.togetherStock.post.domain.Post;
import com.stock.togetherStock.post.domain.PostDto;
import com.stock.togetherStock.post.exception.PostErrorCode;
import com.stock.togetherStock.post.exception.PostException;
import com.stock.togetherStock.post.repository.PostRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    /**
     * 댓글 작성 로직
     */
    @Transactional
    public Comment write(PostDto postDto, MemberDto memberDto, CommentDto commentDto) {

        //commentDto에 member와 post를 저장.
        commentDto.setMemberDto(memberDto);
        commentDto.setPostDto(postDto);

        Comment comment = Comment.builder()
            .member(commentDto.getMemberDto().toEntity())
            .post(commentDto.getPostDto().toEntity())
            .commentContent(commentDto.getCommentContent())
            .regiCommentDate(LocalDateTime.now())
            .build();

        return commentRepository.save(comment);
    }

    /**
     * 댓글 수정
     */
    @Transactional
    public Long commentUpdate(Long commentId, CommentDto commentDto) {

        Comment comment = commentRepository.findByCommentId(commentId)
            .orElseThrow(() -> new PostException(PostErrorCode.POST_NO_POST));

        comment.update(commentDto.getCommentContent());

        return commentId;
    }
}
