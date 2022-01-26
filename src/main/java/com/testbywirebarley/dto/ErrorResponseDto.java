package com.testbywirebarley.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponseDto {

    private String code;
    private String message;

    @Builder
    public ErrorResponseDto(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
