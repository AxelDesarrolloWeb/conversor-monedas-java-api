package com.alvax.conversorMonedas.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;

public class ExchangeRateAPI {
    private static final String API_KEY = "6e14fda10c45b22b9c1f3a6b";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public ExchangeRateApiResponse getExchangeRates(String baseCurrency) {
        String url = BASE_URL + API_KEY + "/latest/" + baseCurrency;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(
                    request, HttpResponse.BodyHandlers.ofString()
            );

            Gson gson = new Gson();
            return gson.fromJson(response.body(), ExchangeRateApiResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener tasas de cambio: " + e.getMessage());
        }
    }
}