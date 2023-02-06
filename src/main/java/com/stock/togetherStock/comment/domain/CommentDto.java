package com.stock.togetherStock.comment.domain;

import com.stock.togetherStock.member.domain.Member;
import com.stock.togetherStock.post.domain.Post;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class CommentDto {

    private long commentId;

    private String commentContent;

    private LocalDateTime regiCommentDate;

    private LocalDateTime updateCommentDate;

    private Member member;

    private Post post;


    public Comment toEntity() {
        return Comment.builder()
            .commentContent(commentContent)
            .regiCommentDate(LocalDateTime.now())
            .member(member)
            .post(post)
            .build();
    }
}
