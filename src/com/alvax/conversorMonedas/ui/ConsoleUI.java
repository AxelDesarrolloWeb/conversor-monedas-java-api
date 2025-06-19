package com.alvax.conversorMonedas.ui;

import com.alvax.conversorMonedas.service.CurrencyService;
import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner;
    private final CurrencyService currencyService;

    public ConsoleUI(CurrencyService currencyService) {
        this.scanner = new Scanner(System.in);
        this.currencyService = currencyService;
    }

    public void start() {
        System.out.println("Sea bienvenido/a al Conversor de Moneda =]\n");

        int opcion;
        do {
            mostrarMenu();
            System.out.print("Elija una opción válida: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            if (opcion >= 1 && opcion <= 6) {
                procesarOpcion(opcion);
            } else if (opcion != 7) {
                System.out.println("\nOpción inválida. Intente nuevamente.\n");
            }
        } while (opcion != 7);

        System.out.println("\n¡Gracias por usar el Conversor de Monedas!");
        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("1) Dólar => Peso argentino");
        System.out.println("2) Peso argentino => Dólar");
        System.out.println("3) Dólar => Real brasileño");
        System.out.println("4) Real brasileño => Dólar");
        System.out.println("5) Dólar => Peso colombiano");
        System.out.println("6) Peso colombiano => Dólar");
        System.out.println("7) Salir\n");
    }

    private void procesarOpcion(int opcion) {
        String fromCurrency, toCurrency;
        switch (opcion) {
            case 1:
                fromCurrency = "USD";
                toCurrency = "ARS";
                break;
            case 2:
                fromCurrency = "ARS";
                toCurrency = "USD";
                break;
            case 3:
                fromCurrency = "USD";
                toCurrency = "BRL";
                break;
            case 4:
                fromCurrency = "BRL";
                toCurrency = "USD";
                break;
            case 5:
                fromCurrency = "USD";
                toCurrency = "COP";
                break;
            case 6:
                fromCurrency = "COP";
                toCurrency = "USD";
                break;
            default:
                throw new IllegalArgumentException("Opción no válida");
        }

        System.out.print("\nIngrese la cantidad a convertir: ");
        double cantidad = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer

        try {
            double resultado = currencyService.convert(fromCurrency, toCurrency, cantidad);
            System.out.printf("\n%.2f %s = %.2f %s\n\n", cantidad, fromCurrency, resultado, toCurrency);
        } catch (IllegalArgumentException e) {
            System.out.println("\nError: " + e.getMessage() + "\n");
        }
    }
}