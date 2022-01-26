package com.testbywirebarley.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testbywirebarley.dto.ResponseApiDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CurrencylayerApiClient {

    private ObjectMapper objectMapper = new ObjectMapper();
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
            e.printStackTrace();
        }
        return responseApiDto;
    }
}
