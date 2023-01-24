package com.stock.togetherStock.post.exception;

import com.stock.togetherStock.common.BaseException;
import com.stock.togetherStock.member.exception.MemberErrorCode;

public class PostException extends BaseException {

    public PostException(PostErrorCode postErrorCode) {
        super(postErrorCode.name(), postErrorCode.getMessage());
    }
}
