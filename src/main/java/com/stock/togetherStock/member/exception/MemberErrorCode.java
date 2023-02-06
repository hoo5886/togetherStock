package com.stock.togetherStock.member.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode {

    ALREADY_EXIST_EMAIL("이미 존재하는 이메일입니다."),
    MEMBER_NOT_FOUND("존재하는 회원이 없습니다."),
    MEMBER_NEED_LOGIN("로그인을 해야 댓글을 작성할 수 있습니다.");

    private final String message;
}
