package com.stock.togetherStock.comment.domain;


import com.stock.togetherStock.member.domain.Member;
import com.stock.togetherStock.post.domain.Post;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@AllArgsConstructor
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Column
    private String commentContent;

    @Column
    private LocalDateTime regiCommentDate;

    @Column
    private LocalDateTime updateCommentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_Id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_Id")
    private Post post;

    public void update(String content) {
        this.commentContent = content;
        this.updateCommentDate = LocalDateTime.now();
    }

    public CommentDto toDto() {
        CommentDto commentDto = CommentDto.builder()
            .commentId(commentId)
            .commentContent(commentContent)
            .regiCommentDate(regiCommentDate)
            .updateCommentDate(updateCommentDate)
            .memberDto(member.toMemberDto())
            .postDto(post.toPostDto())
            .build();
        return commentDto;
    }

}
