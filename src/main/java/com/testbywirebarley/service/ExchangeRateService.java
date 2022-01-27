package com.testbywirebarley.service;

import com.testbywirebarley.dto.ReceivableAmountRequestDto;

public interface ExchangeRateService {
    String getExchangeRate(String currency);

    String getTotalAmount(ReceivableAmountRequestDto receivableAmount);
}
