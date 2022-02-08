package com.testbywirebarley.service;

import com.testbywirebarley.dto.ReceivableAmountRequestDto;
import com.testbywirebarley.dto.ResponseApiDto;
import com.testbywirebarley.exception.CommonException;
import com.testbywirebarley.model.Currency;
import com.testbywirebarley.service.api.CurrencylayerApiClient;
import org.junit.jupiter.api.Test;

import java.text.NumberFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExchangeRateServiceTest {

    ExchangeRateService exchangeRateService = new ExchangeRateServiceImpl();

    CurrencylayerApiClient apiClient = new CurrencylayerApiClient();

    @Test
    void getExchangeRate() {
        String currency = "JPY";

        ResponseApiDto apiDto = apiClient.getExchangeRate(currency);
        double exchange = Currency.valueOf(currency).hasCurrencyCode(apiDto.getQuotes());

        String exchangeRate = convertNumberFormat(exchange);
        System.out.println(apiDto);
        System.out.println(exchange);
        System.out.println(exchangeRate);
    }

    @Test
    void getTotalAmount() {

        ReceivableAmountRequestDto receivableAmount = ReceivableAmountRequestDto
                .builder()
                .exchangeValue("KRW")
                .sendAmount("1,000.00")
                .exchangeValue("1,200.10")
                .build();
        double sendAmount = convertStringToDouble(receivableAmount.getSendAmount());
        checkNumberRange(sendAmount);
        double exchangeRate = convertStringToDouble(receivableAmount.getExchangeValue());
        double receivableTotalAmount = getReceivableTotalAmount(sendAmount, exchangeRate);
        String totalAmountFormat = convertNumberFormat(receivableTotalAmount);

        assertThat(totalAmountFormat).isEqualTo("1,200,100.00");
    }

    @Test
    void getTotalAmount_송금액_범위확인() {
        CommonException exception = assertThrows(CommonException.class, () -> checkNumberRange(-1));
        String errorMessage = exception.getErrorMessage();
        System.out.println(errorMessage);
        assertThat(errorMessage).isEqualTo("송금액이 바르지 않습니다");
    }

    @Test
    void getTotalAmount_송금액_빈값체크() {
        CommonException exception = assertThrows(CommonException.class, () -> convertStringToDouble(""));
        String errorMessage = exception.getErrorMessage();
        System.out.println(errorMessage);
        assertThat(errorMessage).isEqualTo("송금액이 바르지 않습니다");
    }

    @Test
    void getTotalAmount_송금액_문자_체크() {
        CommonException exception = assertThrows(CommonException.class, () -> convertStringToDouble("dths"));
        String errorMessage = exception.getErrorMessage();
        System.out.println(errorMessage);
        assertThat(errorMessage).isEqualTo("송금액이 바르지 않습니다");
    }

    private double getReceivableTotalAmount(double sendAmount, double exchangeRate) {
        double receivableTotalAmount = exchangeRate * sendAmount;
        return receivableTotalAmount;
    }

    private double checkNumberRange(double input) {
        checkMax(input);
        checkMin(input);
        return input;
    }

    private String convertNumberFormat(double input) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
        return numberFormat.format(Double.valueOf(input));
    }

    private double convertStringToDouble(String input) {
        double aDouble = 0;
        try {
            aDouble = Double.parseDouble(input.replaceAll(",", ""));
        }catch (ClassCastException | NumberFormatException e) {
            throw new CommonException("NOT_SUPPORT", "송금액이 바르지 않습니다");
        }catch (NullPointerException e) {
            throw new CommonException("NOT_SUPPORT", "송금액이 바르지 않습니다");
        }
        return aDouble;
    }

    private void checkMax(double sendAmount) {
        if (sendAmount > 10000) {
            throw new CommonException("NOT_SUPPORT", "송금액이 바르지 않습니다");
        }
    }

    private void checkMin(double sendAmount) {
        if (sendAmount < 0) {
            throw new CommonException("NOT_SUPPORT", "송금액이 바르지 않습니다");
        }
    }
}