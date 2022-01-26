package com.testbywirebarley.model;

import com.testbywirebarley.util.ConvertNumber;
import lombok.Builder;

public class ReceivableAmount {

    private String countryOption;
    private int sendAmount;
    private String exchangeValue;

    public String getCountryOption() {
        return countryOption;
    }

    public int getSendAmount() {
        return sendAmount;
    }

    public String getExchangeValue() {
        return exchangeValue;
    }

    @Builder
    public ReceivableAmount(String countryOption, int sendAmount, String exchangeValue) {
        this.countryOption = countryOption;
        this.sendAmount = sendAmount;
        this.exchangeValue = exchangeValue;
    }
}
