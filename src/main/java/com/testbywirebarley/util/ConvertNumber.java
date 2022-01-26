package com.testbywirebarley.util;

import com.testbywirebarley.util.exception.CommonException;
import lombok.extern.log4j.Log4j2;

import java.text.NumberFormat;

@Log4j2
public class ConvertNumber {

    public int checkNumberRange(int input) {
        checkMax(input);
        checkMin(input);
        return input;
    }

    public String convertNumberFormat(String input) {
        if (input != null) {
            NumberFormat numberFormat = NumberFormat.getInstance();
            numberFormat.setMinimumFractionDigits(2);
            numberFormat.setMaximumFractionDigits(2);
            return numberFormat.format(Double.valueOf(input));
        }
        return null;
    }
    public double convertStringToDouble(String input) {
        double aDouble = 0;
        try {
            aDouble = Double.parseDouble(input.replaceAll("\\,", ""));
        }catch (ClassCastException e) {
            log.error("convertStringToDouble ClassCastException {}", e.getMessage());
            throw new CommonException("NOT_SUPPORT", "송금액이 바르지 않습니다");
        }catch (NullPointerException e) {
            log.error("convertStringToDouble NullPointerException {}", e.getMessage());
            throw new CommonException("NOT_SUPPORT", "송금액이 바르지 않습니다");
        }catch (NumberFormatException e) {
            log.error("convertStringToDouble NullPointerException {}", e.getMessage());
            throw new CommonException("NOT_SUPPORT", "송금액이 바르지 않습니다");
        }
        return aDouble;
    }

    private void checkMax(int sendAmount) {
        if (sendAmount > 10000) {
            log.error("checkMax Exception");
            throw new CommonException("NOT_SUPPORT", "송금액이 바르지 않습니다");
        }
    }

    private void checkMin(int sendAmount) {
        if (sendAmount < 0) {
            log.error("checkMin Exception");
            throw new CommonException("NOT_SUPPORT", "송금액이 바르지 않습니다");
        }
    }
}
