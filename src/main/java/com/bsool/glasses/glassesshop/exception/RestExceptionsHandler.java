package com.bsool.glasses.glassesshop.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class RestExceptionsHandler {

    @ExceptionHandler(GlassesShopException.class)
    public ResponseEntity<RestErrorResponse> handleGlassShopException(final GlassesShopException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(RestErrorResponse.builder()
                        .errorCode("401")
                        .errorMessage(exception.getErrorMessage())
                        .build()
                );
    }
}
