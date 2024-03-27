package com.project.music.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("business exception message={}", e.getMessage());
        log.error(e.getMessage(), e);
        return Result.fail(Globals.HTTP_ERROR, e.getMessage());
    }

}
