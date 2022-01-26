package com.testbywirebarley.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

class convertNumberTest {

    private double exchangeRate;
    private int sendAmount;
    private int totalAmount;

    @BeforeEach
    void setUp() {
        exchangeRate = 1195.755008;
        sendAmount = 100;
    }

    @AfterEach
    void tearDown() {
        exchangeRate = 0;
        sendAmount = 0;
    }

    @Test
    void checkNumberRange() {
        sendAmount = 10000;
        boolean isAbleNumber = true;
        isAbleNumber = checkMax(sendAmount);
        isAbleNumber = checkMin(sendAmount);
        assertThat(false).isEqualTo(isAbleNumber);
    }

    @Test
    void convertNumberToString() {
        double totalAmount = exchangeRate * sendAmount;
        String conversion = getConversion(String.valueOf(totalAmount));
        System.out.println(conversion);
    }

    private String getConversion(String totalAmount) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.forLanguageTag("###,###.##"));
//        numberFormat.setMinimumFractionDigits(2);
//        numberFormat.setMaximumFractionDigits(2);
        String conversion = numberFormat.format(Double.valueOf(totalAmount));
        return conversion;
    }

    private boolean checkMax(int sendAmount) {
        return sendAmount > 10000;
    }

    private boolean checkMin(int sendAmount) {
        return sendAmount < 0;
    }
}