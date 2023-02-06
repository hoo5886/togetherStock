package com.stock.togetherStock.member.domain;

import com.stock.togetherStock.comment.domain.Comment;
import com.stock.togetherStock.post.domain.Post;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
public class Member implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String nickname;

    @Column
    private String phone;

    @Column
    private String intro; //짧은 자기소개

    @Column
    private LocalDateTime regiMemDate;

    @Column
    private LocalDateTime updateMemDate;

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> Comments = new ArrayList<>();

    public MemberDto toMemberDto() {

        return MemberDto.builder()
            .memberId(memberId)
            .password(password)
            .name(name)
            .nickname(nickname)
            .phone(phone)
            .intro(intro)
            .build();
    }

    public void update(String name, String nickname, String phone, String intro) {
        this.name = name;
        this.nickname = nickname;
        this.phone = phone;
        this.intro = intro;
        this.updateMemDate = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
