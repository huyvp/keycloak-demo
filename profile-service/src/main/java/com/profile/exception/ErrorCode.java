package com.profile.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    ACCESS_DENIED_EXCEPTION(9999, "Access denied error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(1009, "Username existed, please choose another one", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(1008, "Email existed, please choose another one", HttpStatus.BAD_REQUEST),
    USERNAME_IS_MISSING(1010, "Please enter username", HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    private HttpStatus status;
}
