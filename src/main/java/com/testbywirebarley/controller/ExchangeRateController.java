package com.testbywirebarley.controller;

import com.testbywirebarley.model.ReceivableAmount;
import com.testbywirebarley.dto.ResponseApiDto;
import com.testbywirebarley.dto.ResultMessageDto;
import com.testbywirebarley.service.ExchangeRateService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping(value = "/exchange/country/{currency}")
    public ResponseEntity<ResultMessageDto> findExchangeRate(@PathVariable String currency) {
        ResponseApiDto responseApiDto = exchangeRateService.getExchangeRate(currency);
        return ResponseEntity.ok(new ResultMessageDto(responseApiDto, null));
    }

    @PostMapping(value = "/exchange/calculation")
    public ResponseEntity<ResultMessageDto> calculateTotalAmount(@RequestBody ReceivableAmount receivableAmount) {
        String totalAmount = exchangeRateService.getTotalAmount(receivableAmount);
        String result = "수취금액은 " + totalAmount + " " + receivableAmount.getCountryOption() + " " + "입니다.";
        return ResponseEntity.ok(new ResultMessageDto(result, null));
    }
}
