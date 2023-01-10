package com.stock.togetherStock.model.Dto;

import com.stock.togetherStock.model.Member;
import com.stock.togetherStock.model.Post;
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
