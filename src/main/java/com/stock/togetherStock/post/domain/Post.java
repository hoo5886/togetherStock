package com.stock.togetherStock.post.domain;

import com.stock.togetherStock.member.domain.Member;
import com.stock.togetherStock.comment.Comment;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue
    private long postId;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private LocalDateTime regiPostDate;

    @Column
    private LocalDateTime updatePostDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @OneToMany(mappedBy = "comment")
    private List<Comment> Comments = new ArrayList<>();

    public PostDto toPostDto() {
        return PostDto.builder()
            .postId(postId)
            .title(title)
            .content(content)
            .build();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        this.updatePostDate = LocalDateTime.now();
    }
}
