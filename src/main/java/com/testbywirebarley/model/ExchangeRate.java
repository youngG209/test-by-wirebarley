package com.testbywirebarley.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public class ExchangeRate {

    @JsonProperty("USDKRW")
    private double usdkrw;
    @JsonProperty("USDJPY")
    private double usdjpy;
    @JsonProperty("USDPHP")
    private double usdphp;

    public ExchangeRate() {
    }

    public ExchangeRate(double usdkrw, double usdjpy, double usdphp) {
        this.usdkrw = usdkrw;
        this.usdjpy = usdjpy;
        this.usdphp = usdphp;
    }

    public double getUsdkrw() {
        return usdkrw;
    }

    public double getUsdjpy() {
        return usdjpy;
    }

    public double getUsdphp() {
        return usdphp;
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
