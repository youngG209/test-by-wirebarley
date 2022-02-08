package com.testbywirebarley.service.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testbywirebarley.dto.ResponseApiDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

class CurrencylayerApiClientTest {

    private static final String ACCESS_KEY = "066d66550637011e21dbb3afa759689c";

    @Test
    void getExchangeRate() throws JsonProcessingException {
        String currencylayerApiUrl = "http://api.currencylayer.com/live";
        WebClient webClient = WebClient.builder().baseUrl(currencylayerApiUrl).build();
        String block = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("access_key", ACCESS_KEY)
                        .queryParam("currencies", "KRW")
                        .queryParam("source", "USD")
                        .queryParam("format", "1")
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class).block();//                .toEntity(ExchangeRateDto.class).block()

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ResponseApiDto responseApiDto = objectMapper.readValue(block, ResponseApiDto.class);

        System.out.println("결과 : "+ responseApiDto.getQuotes().toString());
//        System.out.println("결과 : "+block.getSuccess().getClass().getName());
//        System.out.println("결과 : "+block.getTerms().getClass().getName());
//        System.out.println("결과 : "+block.getPrivacy().getClass().getName());
//        System.out.println("결과 : "+block.getTimestamp().getClass().getName());
//        System.out.println("결과 : "+block.getQuotes().get("USDKRW").getClass().getName());
    }
}