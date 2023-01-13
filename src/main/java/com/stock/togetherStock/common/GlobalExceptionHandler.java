package com.stock.togetherStock.common;

import com.stock.togetherStock.common.response.ErrorArgumentResponse;
import com.stock.togetherStock.common.response.ErrorResponse;
import com.stock.togetherStock.member.exception.MemberException;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<ErrorResponse> memberException(MemberException e) {
        log.error("memberException : ", e);

        return ResponseEntity.ok().body(new ErrorResponse(e.getErrorCode(), e.getErrorMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorArgumentResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("methodArgumentNotValidException : ", e);

        Map<String, String> errorMaps = e.getFieldErrors()
            .stream()
            .collect(Collectors.toMap(
                i1 -> i1.getField(),
                i2 -> i2.getDefaultMessage())
            );


        return ResponseEntity.ok().body(new ErrorArgumentResponse("ERROR-100", "유효성 검사 실패", errorMaps));
    }


}
