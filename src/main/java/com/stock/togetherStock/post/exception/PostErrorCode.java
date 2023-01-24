package com.stock.togetherStock.post.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostErrorCode {

    POST_ERROR_CODE("존재하는 게시글이 없습니다.");

    private final String message;
}
