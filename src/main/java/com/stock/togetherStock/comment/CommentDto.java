package com.stock.togetherStock.comment;

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

    private String comment;

    private LocalDateTime regiCommentDate;

    private Member member;

    private Post post;

}
