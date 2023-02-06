package com.stock.togetherStock.comment.service;

import com.stock.togetherStock.comment.domain.Comment;
import com.stock.togetherStock.comment.domain.CommentDto;
import com.stock.togetherStock.comment.repository.CommentRepository;
import com.stock.togetherStock.member.domain.Member;
import com.stock.togetherStock.member.repository.MemberRepository;
import com.stock.togetherStock.post.domain.Post;
import com.stock.togetherStock.post.repository.PostRepository;
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
    public Comment write(Post post, Member member, CommentDto commentDto) {

        commentDto.setMember(member);
        commentDto.setPost(post);

        Comment comment = Comment.builder()
            .member(commentDto.getMember())
            .post(commentDto.getPost())
            .build();

        return commentRepository.save(comment);
    }


}
