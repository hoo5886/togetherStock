package com.stock.togetherStock.post.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostErrorCode {

    POST_NO_POST("존재하는 게시글이 없습니다."),
    POST_NEED_LOGIN("게시글을 작성하려면 로그인 정보가 필요합니다.");

    private final String message;
}
