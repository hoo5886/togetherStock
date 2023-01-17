package com.stock.togetherStock.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class BaseException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;
}
