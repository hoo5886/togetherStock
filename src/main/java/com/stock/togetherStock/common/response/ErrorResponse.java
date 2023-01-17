package com.stock.togetherStock.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String errorCode;
    private String errorMessage;
}
