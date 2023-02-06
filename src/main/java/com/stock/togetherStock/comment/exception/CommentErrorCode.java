package com.stock.togetherStock.comment.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommentErrorCode {

    MEMBER_NOT_FOUND("존재하는 회원이 없습니다.");

    private final String message;
}
