package com.testbywirebarley.service;

import com.testbywirebarley.dto.ReceivableAmountRequestDto;
import com.testbywirebarley.model.Currency;
import com.testbywirebarley.service.api.CurrencylayerApiClient;
import com.testbywirebarley.dto.ResponseApiDto;
import com.testbywirebarley.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;

@Slf4j
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final CurrencylayerApiClient apiClient = new CurrencylayerApiClient();

    public String getExchangeRate(String currency) {
        ResponseApiDto apiDto = apiClient.getExchangeRate(currency);
        double exchange = Currency.valueOf(currency).hasCurrencyCode(apiDto.getQuotes());

        return convertNumberFormat(exchange);
    }

    public String getTotalAmount(ReceivableAmountRequestDto receivableAmount) {
        double sendAmount = convertStringToDouble(receivableAmount.getSendAmount());
        checkNumberRange(sendAmount);
        double exchangeRate = convertStringToDouble(receivableAmount.getExchangeValue());
        double receivableTotalAmount = getReceivableTotalAmount(sendAmount, exchangeRate);
        String totalAmountFormat = convertNumberFormat(receivableTotalAmount);

        return "수취금액은 " + totalAmountFormat + " " + receivableAmount.getCountryOption() + " " + "입니다.";
    }

    private double getReceivableTotalAmount(double sendAmount, double exchangeRate) {
        return exchangeRate * sendAmount;
    }

    private void checkNumberRange(double input) {
        checkMax(input);
        checkMin(input);
    }

    private String convertNumberFormat(double input) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
        return numberFormat.format(Double.valueOf(input));
    }

    private double convertStringToDouble(String input) {
        double aDouble;
        try {
            aDouble = Double.parseDouble(input.replaceAll(",", ""));
        }catch (ClassCastException e) {
            log.error("convertStringToDouble ClassCastException {}", e.getMessage());
            throw new CommonException("NOT_SUPPORT", "송금액이 바르지 않습니다");
        }catch (NullPointerException | NumberFormatException e) {
            log.error("convertStringToDouble NullPointerException {}", e.getMessage());
            throw new CommonException("NOT_SUPPORT", "송금액이 바르지 않습니다");
        }
        return aDouble;
    }

    private void checkMax(double sendAmount) {
        if (sendAmount > 10000) {
            log.error("checkMax Exception");
            throw new CommonException("NOT_SUPPORT", "송금액이 바르지 않습니다");
        }
    }

    private void checkMin(double sendAmount) {
        if (sendAmount < 0) {
            log.error("checkMin Exception");
            throw new CommonException("NOT_SUPPORT", "송금액이 바르지 않습니다");
        }
    }
}
