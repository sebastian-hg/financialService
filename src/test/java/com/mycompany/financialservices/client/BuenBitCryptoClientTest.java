package com.mycompany.financialservices.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.financialservices.client.impl.BuenBitCryptoClient;
import com.mycompany.financialservices.configuration.ClientConfigProperties;
import com.mycompany.financialservices.model.dto.response.CryptoDto;
import com.mycompany.financialservices.model.dto.response.CryptoResponseDto;
import com.mycompany.financialservices.model.dto.response.ObjectDto;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)

//@TestPropertySource(properties = "client.cryptoUrl=https://be.buenbit.com/api/market/tickers")
class BuenBitCryptoClientTest {
    private MockWebServer server;
    private String getUrl;
    private Mono<CryptoResponseDto> response;
    private CryptoResponseDto expectedResponse;

    @Mock
    private ClientConfigProperties properties;

    @InjectMocks
    private BuenBitCryptoClient client;

    @BeforeEach
    void setUp() {
        server = new MockWebServer();
    }

    @Test
    void getCryptoIsAOK() throws IOException, InterruptedException {
        givenExpectedResponse();
        givenGetUrl();
        givenWebService();
        givenProperties();
        whenExecute();
        thenGetIsAOk();

    }

    private void givenExpectedResponse() {
        CryptoDto cryptoDto = CryptoDto.builder()
                .askCurrency("ARS")
                .bidCurrency("BTC")
                .currency("ars")
                .marketIdentifier("btcars")
                .purchasePrice(6263625.99)
                .sellingPrice("58505944")
                .build();
        expectedResponse = CryptoResponseDto.builder()
                .object(ObjectDto.builder()
                        .btcArs(cryptoDto)
                        .btcDai(cryptoDto)
                        .daiArs(cryptoDto)
                        .daiUsd(cryptoDto)
                        .ethArs(cryptoDto)
                        .ethDai(cryptoDto)
                        .build())
                .build();
    }

    private void givenGetUrl() {
        getUrl = "";
    }

    private void givenWebService() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(expectedResponse);
        server.enqueue(
                new MockResponse()
                        .setResponseCode(200)
                        .setHeader(HttpHeaders.ACCEPT , MediaType.APPLICATION_JSON_VALUE)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody(jsonResponse));
        server.start(8081);

    }
    private void givenProperties(){
        when(properties.getCryptoUrl()).thenReturn("/");
    }

    private void whenExecute() {
        response = client.execute();

    }


    private void thenGetIsAOk() throws InterruptedException {
        StepVerifier.create(response)
                .expectNextMatches(result -> result.equals(expectedResponse))
                .verifyComplete();
        RecordedRequest request = server.takeRequest();
        Assertions.assertEquals(getUrl, request.getPath());
    }

    @AfterEach
    void tearDown() throws IOException {
        server.shutdown();
    }
}
