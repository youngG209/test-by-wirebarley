package com.testbywirebarley.controller;

import com.testbywirebarley.dto.ReceivableAmountRequestDto;
import com.testbywirebarley.dto.ResultMessageDto;
import com.testbywirebarley.service.ExchangeRateServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExchangeRateController {
    private final ExchangeRateServiceImpl exchangeRateService;

    public ExchangeRateController(ExchangeRateServiceImpl exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping(value = "/exchange/country/{currency}")
    public ResponseEntity<ResultMessageDto> findExchangeRate(@PathVariable String currency) {
        String exchangeRate = exchangeRateService.getExchangeRate(currency);
        return ResponseEntity.ok(new ResultMessageDto(exchangeRate, null));
    }

    @PostMapping(value = "/exchange/calculation")
    public ResponseEntity<ResultMessageDto> calculateTotalAmount(@RequestBody ReceivableAmountRequestDto receivableAmount) {
        String result = exchangeRateService.getTotalAmount(receivableAmount);
        return ResponseEntity.ok(new ResultMessageDto(result, null));
    }
}
