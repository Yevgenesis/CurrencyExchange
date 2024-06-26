package exchange.component.service;


import exchange.exception.NegativeIncomeException;
import exchange.model.Currency;

import java.util.InputMismatchException;
import java.util.Scanner;

import static exchange.model.Color.*;

public class UserInterface {

    public final Scanner scanner;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    public Currency chooseExchangeService() {
        while (true) {
            // Вывод в цвете списка доступных вариантов обмена
            System.out.println("1" + GREEN + " - " + PURPLE + "USD " + GREEN + "-> " + PURPLE + "EUR: " + RESET);
            System.out.println("2" + GREEN + " - " + PURPLE + "EUR " + GREEN + "-> " + PURPLE + "USD: ");
            System.out.print(GREEN + "Chose your service: " + RESET);

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    return Currency.USD;
                case "2":
                    return Currency.EUR;
                default:
                    System.out.println(RED + "Wrong service chose!\n" + RESET);

            }
        }
    }


    // Метод для ввода суммы обмена от пользователя
    public double enterAmount() {
        while (true) {
            System.out.print(GREEN + "Input amount: " + RESET);
            try {
                double amount = scanner.nextDouble();
                scanner.nextLine();
                if (amount <= 0) {
                    throw new NegativeIncomeException("Amount must be greater than zero");
                }
                return amount;

            } catch (InputMismatchException e) {
                System.out.println(RED + "Invalid input! Please enter a valid number." + RESET);
                scanner.nextLine();
            } catch (NegativeIncomeException e) {
                System.out.println(RED + e.getMessage() + RESET);

            }
        }
    }

}

