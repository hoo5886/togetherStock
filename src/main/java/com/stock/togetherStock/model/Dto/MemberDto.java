package com.stock.togetherStock.model.Dto;

import com.stock.togetherStock.model.Comment;
import com.stock.togetherStock.model.Post;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor()
@Builder
@AllArgsConstructor
public class MemberDto {

    private Long memberId;

    @NotEmpty(message = "이메일은 필수입니다.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수입니다.")
    private String password;

    @NotEmpty(message = "회원이름은 필수입니다.")
    private String name;

    @NotEmpty(message = "닉네임은 필수입니다.")
    private String nickname;

    private String phone;

    private String intro; //자기소개

    private LocalDateTime regiMemDate;

    private List<Post> posts = new ArrayList<>();

    private List<Comment> Comments = new ArrayList<>();

}
