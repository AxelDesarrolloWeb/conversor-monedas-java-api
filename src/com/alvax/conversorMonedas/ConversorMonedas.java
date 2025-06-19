//package com.alvax.conversorMonedas;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.util.Scanner;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//
//public class ConversorMonedas {
//
//    private static final String API_KEY = "6e14fda10c45b22b9c1f3a6b";
//    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";
//
//    public TasasCambio obtenerTasasCambio() {
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(BASE_URL))
//                .build();
//
//        try {
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
//
//            // Verificar si la respuesta es exitosa
//            if (!"success".equals(jsonObject.get("result").getAsString())) {
//                throw new RuntimeException("Error en la API: " + jsonObject.get("result").getAsString());
//            }
//
//            // Extraer las tasas de conversión
//            JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");
//
//            return new Gson().fromJson(rates, TasasCambio.class);
//        } catch (IOException | InterruptedException e) {
//            throw new RuntimeException("Error al obtener tasas de cambio: " + e.getMessage());
//        }
//    }
//
//    public static class TasasCambio {
//        private double ARS;  // Peso Argentino
//        private double BRL;  // Real Brasileño
//        private double COP;  // Peso Colombiano
//
//        // Getters
//        public double getARS() { return ARS; }
//        public double getBRL() { return BRL; }
//        public double getCOP() { return COP; }
//
//        // Setters
//        public void setARS(double ARS) { this.ARS = ARS; }
//        public void setBRL(double BRL) { this.BRL = BRL; }
//        public void setCOP(double COP) { this.COP = COP; }
//    }
//
//    public static void main(String[] args) {
//        ConversorMonedas conversor = new ConversorMonedas();
//        Scanner scanner = new Scanner(System.in);
//
//        try {
//            // Obtener tasas de cambio actuales
//            TasasCambio tasas = conversor.obtenerTasasCambio();
//
//            System.out.println("Sea bienvenido/a al Conversor de Moneda =]\n");
//
//            int opcion;
//            do {
//                mostrarMenu();
//                System.out.print("Elija una opción válida: ");
//                opcion = scanner.nextInt();
//
//                if (opcion >= 1 && opcion <= 6) {
//                    System.out.print("\nIngrese la cantidad a convertir: ");
//                    double cantidad = scanner.nextDouble();
//                    realizarConversion(opcion, cantidad, tasas);
//                } else if (opcion != 7) {
//                    System.out.println("\nOpción inválida. Intente nuevamente.\n");
//                }
//            } while (opcion != 7);
//
//            System.out.println("\n¡Gracias por usar el Conversor de Monedas!");
//        } catch (RuntimeException e) {
//            System.err.println("Error: " + e.getMessage());
//            System.err.println("Usando tasas de cambio por defecto...");
//            // Aquí podrías implementar un fallback con tasas fijas
//        } finally {
//            scanner.close();
//        }
//    }
//
//    private static void mostrarMenu() {
//        System.out.println("1) Dólar => Peso argentino");
//        System.out.println("2) Peso argentino => Dólar");
//        System.out.println("3) Dólar => Real brasileño");
//        System.out.println("4) Real brasileño => Dólar");
//        System.out.println("5) Dólar => Peso colombiano");
//        System.out.println("6) Peso colombiano => Dólar");
//        System.out.println("7) Salir\n");
//    }
//
//    private static void realizarConversion(int opcion, double cantidad, TasasCambio tasas) {
//        double resultado = 0;
//        String conversion = "";
//
//        switch (opcion) {
//            case 1: // USD => ARS
//                resultado = cantidad * tasas.getARS();
//                conversion = String.format("%.2f USD = %.2f ARS", cantidad, resultado);
//                break;
//            case 2: // ARS => USD
//                resultado = cantidad / tasas.getARS();
//                conversion = String.format("%.2f ARS = %.2f USD", cantidad, resultado);
//                break;
//            case 3: // USD => BRL
//                resultado = cantidad * tasas.getBRL();
//                conversion = String.format("%.2f USD = %.2f BRL", cantidad, resultado);
//                break;
//            case 4: // BRL => USD
//                resultado = cantidad / tasas.getBRL();
//                conversion = String.format("%.2f BRL = %.2f USD", cantidad, resultado);
//                break;
//            case 5: // USD => COP
//                resultado = cantidad * tasas.getCOP();
//                conversion = String.format("%.2f USD = %.2f COP", cantidad, resultado);
//                break;
//            case 6: // COP => USD
//                resultado = cantidad / tasas.getCOP();
//                conversion = String.format("%.2f COP = %.2f USD", cantidad, resultado);
//                break;
//        }
//
//        System.out.println("\n" + conversion + "\n");
//    }
//}