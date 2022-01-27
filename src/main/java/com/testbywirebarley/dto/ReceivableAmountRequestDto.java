package com.testbywirebarley.dto;

import lombok.Builder;

public class ReceivableAmountRequestDto {

    private final String countryOption;
    private final String sendAmount;
    private final String exchangeValue;

    public String getCountryOption() {
        return countryOption;
    }

    public String getSendAmount() {
        return sendAmount;
    }

    public String getExchangeValue() {
        return exchangeValue;
    }

    @Builder
    public ReceivableAmountRequestDto(String countryOption, String sendAmount, String exchangeValue) {
        this.countryOption = countryOption;
        this.sendAmount = sendAmount;
        this.exchangeValue = exchangeValue;
    }
}
