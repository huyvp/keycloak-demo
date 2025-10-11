package com.profile.exception;

import com.profile.dto.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> handleException(ServiceException e) {
        ErrorCode errorCode = e.getErrorCode();
        CommonResponse<?> commonResponse = new CommonResponse<>();
        commonResponse.setCode(errorCode.getCode());
        commonResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatus()).body(commonResponse);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<?> handleException(AccessDeniedException e) {
        ErrorCode errorCode = ErrorCode.ACCESS_DENIED_EXCEPTION;
        CommonResponse<?> commonResponse = new CommonResponse<>();
        commonResponse.setCode(errorCode.getCode());
        commonResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatus()).body(commonResponse);
    }
}
