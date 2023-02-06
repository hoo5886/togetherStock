package com.stock.togetherStock.comment.exception;

import com.stock.togetherStock.common.BaseException;

public class CommentException extends BaseException {

    public CommentException(CommentErrorCode memberErrorCode) {
        super(memberErrorCode.name(), memberErrorCode.getMessage());
    }
}
