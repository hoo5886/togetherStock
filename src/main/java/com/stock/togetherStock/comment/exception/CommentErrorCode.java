package com.stock.togetherStock.comment.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommentErrorCode {

    MEMBER_NOT_FOUND("존재하는 회원이 없습니다."),
    COMMENT_NOT_FOUND("댓글이 존재하지 않습니다.");

    private final String message;
}
