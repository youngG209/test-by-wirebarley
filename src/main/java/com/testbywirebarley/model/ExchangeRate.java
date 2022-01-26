package com.testbywirebarley.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.testbywirebarley.util.ConvertNumber;

public class ExchangeRate {

    private ConvertNumber convertNumber = new ConvertNumber();

    @JsonProperty("USDKRW")
    private String usdkrw;
    @JsonProperty("USDJPY")
    private String usdjpy;
    @JsonProperty("USDPHP")
    private String usdphp;

    public ExchangeRate() {
    }

    public ExchangeRate(String usdkrw, String usdjpy, String usdphp) {
        this.usdkrw = usdkrw;
        this.usdjpy = usdjpy;
        this.usdphp = usdphp;
    }

    public String getUsdkrw() {
        return convertNumber.convertNumberFormat(usdkrw);
    }

    public String getUsdjpy() {
        return convertNumber.convertNumberFormat(usdjpy);
    }

    public String getUsdphp() {
        return convertNumber.convertNumberFormat(usdphp);
    }

    @Override
    public String toString() {
        return "Quotes{" +
                "usdkrw='" + usdkrw + '\'' +
                ", usdjpy='" + usdjpy + '\'' +
                ", usdphp='" + usdphp + '\'' +
                '}';
    }
}
