package com.testbywirebarley.util.exception;

import com.testbywirebarley.dto.ErrorResponseDto;
import com.testbywirebarley.dto.ResultMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResultMessageDto> processValidationError(HttpMessageNotReadableException ex) {
        String message = ex.getMessage();
        log.error(message);
        message = "송금액이 바르지 않습니다";
        ErrorResponseDto error = new ErrorResponseDto("BAD_REQUEST_BODY", message);
        return ResponseEntity.badRequest().body(new ResultMessageDto(null, error));
    }

    @ExceptionHandler(value = {
            CommonException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ResultMessageDto> handleCommonBaseException(CommonException e) {
        String message = e.getErrorMessage();
        log.error(message + "[ " + e.getMessage() + " ]");
        ErrorResponseDto error = new ErrorResponseDto(e.getErrorStatus(), message);
        return ResponseEntity.badRequest().body(new ResultMessageDto(null, error));
    }

}
