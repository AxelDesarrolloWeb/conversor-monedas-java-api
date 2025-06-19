package com.alvax.conversorMonedas;

import com.alvax.conversorMonedas.api.ExchangeRateAPI;
import com.alvax.conversorMonedas.api.ExchangeRateApiResponse;
import com.alvax.conversorMonedas.service.CurrencyService;
import com.alvax.conversorMonedas.ui.ConsoleUI;

public class App {
    public static void main(String[] args) {
        // Obtener los datos de la API
        ExchangeRateAPI api = new ExchangeRateAPI();
        ExchangeRateApiResponse response = api.getExchangeRates("USD");

        // Crear el servicio de conversión
        CurrencyService currencyService = new CurrencyService(response);

        // Iniciar la interfaz de usuario
        ConsoleUI ui = new ConsoleUI(currencyService);
        ui.start();
    }
}