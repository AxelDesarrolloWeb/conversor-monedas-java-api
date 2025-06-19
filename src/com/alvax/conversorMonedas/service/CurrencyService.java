package com.alvax.conversorMonedas.service;

import com.alvax.conversorMonedas.api.ExchangeRateApiResponse;

public class CurrencyService {
    private final ExchangeRateApiResponse exchangeRates;

    public CurrencyService(ExchangeRateApiResponse exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    public double convert(String fromCurrency, String toCurrency, double amount) {
        if (fromCurrency.equals(exchangeRates.base_code())) {
            // Si la moneda origen es la base, usamos directamente la tasa de la moneda destino
            Double toRate = exchangeRates.conversion_rates().get(toCurrency);
            if (toRate == null) {
                throw new IllegalArgumentException("Moneda destino no encontrada: " + toCurrency);
            }
            return amount * toRate;
        } else if (toCurrency.equals(exchangeRates.base_code())) {
            // Si la moneda destino es la base, usamos la inversa de la tasa de la moneda origen
            Double fromRate = exchangeRates.conversion_rates().get(fromCurrency);
            if (fromRate == null) {
                throw new IllegalArgumentException("Moneda origen no encontrada: " + fromCurrency);
            }
            return amount / fromRate;
        } else {
            // Si ninguna es la base, convertimos primero a la base y luego a la moneda destino
            Double fromRate = exchangeRates.conversion_rates().get(fromCurrency);
            Double toRate = exchangeRates.conversion_rates().get(toCurrency);
            if (fromRate == null || toRate == null) {
                String notFound = fromRate == null ? fromCurrency : toCurrency;
                throw new IllegalArgumentException("Moneda no encontrada: " + notFound);
            }
            double amountInBase = amount / fromRate;
            return amountInBase * toRate;
        }
    }
}