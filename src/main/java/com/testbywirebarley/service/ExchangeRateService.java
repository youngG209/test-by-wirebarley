package com.testbywirebarley.service;

import com.testbywirebarley.api.CurrencylayerApiClient;
import com.testbywirebarley.model.ReceivableAmount;
import com.testbywirebarley.dto.ResponseApiDto;
import com.testbywirebarley.util.ConvertNumber;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateService {

    private final CurrencylayerApiClient apiClient;

    public ExchangeRateService(CurrencylayerApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ResponseApiDto getExchangeRate(String currency) {
        return apiClient.getExchangeRate(currency);
    }

    public String getTotalAmount(ReceivableAmount receivableAmount) {
        ConvertNumber convertNumber = new ConvertNumber();

        int sendAmount = receivableAmount.getSendAmount();
        convertNumber.checkNumberRange(sendAmount);
        double exchangeRate = convertNumber.convertStringToDouble(receivableAmount.getExchangeValue());
        double receivableTotalAmount = exchangeRate * sendAmount;
        String totalAmountFormat = convertNumber.convertNumberFormat(String.valueOf(receivableTotalAmount));

        return totalAmountFormat;
    }
}
