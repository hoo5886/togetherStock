package com.stock.togetherStock.common.response;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorArgumentResponse {

    private String errorCode;
    private String errorMessage;

    private Map<String, String> errorMaps = new HashMap<>();

}
