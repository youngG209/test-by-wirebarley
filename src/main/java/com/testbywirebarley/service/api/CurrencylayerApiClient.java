package com.testbywirebarley.service.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testbywirebarley.dto.ResponseApiDto;
import com.testbywirebarley.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CurrencylayerApiClient {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private ResponseApiDto responseApiDto = new ResponseApiDto();
    private static final String ACCESS_KEY = "066d66550637011e21dbb3afa759689c";
    private static final String currencylayerApiUrl = "http://api.currencylayer.com/live";

    public ResponseApiDto getExchangeRate(String currency) {
        WebClient webClient = WebClient.builder().baseUrl(currencylayerApiUrl).build();
        String responseApi = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("access_key", ACCESS_KEY)
                        .queryParam("currencies", currency)
                        .queryParam("source", "USD")
                        .queryParam("format", "1")
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class).block();

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            responseApiDto = objectMapper.readValue(responseApi, ResponseApiDto.class);
        } catch (JsonProcessingException e) {
            log.error("getExchangeRate JsonProcessingException {}", e.getMessage());
            throw new CommonException("NOT_CONNECT_API", "Api통신이 되지 않았습니다.");
        }
        return responseApiDto;
    }
}
