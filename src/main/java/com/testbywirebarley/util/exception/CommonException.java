package com.testbywirebarley.util.exception;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private String errorStatus;
    private String errorMessage;

    public CommonException(String errorStatus, String errorMessage) {
        this.errorStatus = errorStatus;
        this.errorMessage = errorMessage;
    }
}
