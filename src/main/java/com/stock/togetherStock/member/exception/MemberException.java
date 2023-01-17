package com.stock.togetherStock.member.exception;

import com.stock.togetherStock.common.BaseException;

public class MemberException extends BaseException {

    public MemberException(MemberErrorCode memberErrorCode) {
        super(memberErrorCode.name(), memberErrorCode.getMessage());
    }
}
