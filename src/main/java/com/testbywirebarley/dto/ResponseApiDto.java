package com.testbywirebarley.dto;

import com.testbywirebarley.model.ExchangeRate;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResponseApiDto {

    private boolean success;
    private String terms;
    private String privacy;
    private int timestamp;
    private ExchangeRate quotes;

    public ResponseApiDto() {
    }

    public ResponseApiDto(boolean success, String terms, String privacy, int timestamp, ExchangeRate quotes) {
        this.success = success;
        this.terms = terms;
        this.privacy = privacy;
        this.timestamp = timestamp;
        this.quotes = quotes;
    }
}
