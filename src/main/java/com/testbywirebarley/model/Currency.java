package com.testbywirebarley.model;

public enum Currency {
    KRW("usdkrw"){
        @Override
        public double hasCurrencyCode(ExchangeRate quotes) {
            return quotes.getUsdkrw();
        }
    },
    JPY("usdjpy"){
        @Override
        public double hasCurrencyCode(ExchangeRate quotes) {
            return quotes.getUsdjpy();
        }
    },
    PHP("usdphp"){
        @Override
        public double hasCurrencyCode(ExchangeRate quotes) {
            return quotes.getUsdphp();
        }
    };

    private final String exchange;

    Currency(String exchange) {
        this.exchange = exchange;
    }

    public String getExchange() {
        return exchange;
    }

    public abstract double hasCurrencyCode(ExchangeRate quotes);
}
