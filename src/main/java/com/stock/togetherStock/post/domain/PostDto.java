package com.stock.togetherStock.post.domain;

import com.stock.togetherStock.comment.domain.Comment;
import com.stock.togetherStock.member.domain.Member;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {

    private long postId;

    @NotEmpty(message = "제목을 입력해주세요.")
    private String title;

    @NotEmpty(message = "내용을 입력해주세요.")
    private String content;

    private LocalDateTime regiPostDate;

    private Member member;

    private List<Comment> Comments = new ArrayList<>();

    public Post toEntity() {
        return Post.builder()
            .title(title)
            .content(content)
            .member(member)
            .Comments(Comments)
            .build();
    }

}
