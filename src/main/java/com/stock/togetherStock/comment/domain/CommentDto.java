package com.stock.togetherStock.comment.domain;

import com.stock.togetherStock.member.domain.Member;
import com.stock.togetherStock.member.domain.MemberDto;
import com.stock.togetherStock.post.domain.Post;
import com.stock.togetherStock.post.domain.PostDto;
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

    private Long commentId;

    private String commentContent;

    private LocalDateTime regiCommentDate;

    private LocalDateTime updateCommentDate;

    private MemberDto memberDto;

    private PostDto postDto;

    public Comment toEntity() {
        return Comment.builder()
            .commentContent(commentContent)
            .regiCommentDate(LocalDateTime.now())
            .member(memberDto.toEntity())
            .post(postDto.toEntity())
            .build();
    }

    public void update(String content) {
        this.commentContent = content;
        this.updateCommentDate = LocalDateTime.now();
    }
}
