package exchange;

import exchange.component.service.UserInterface;
import exchange.component.wrapper.ConverterWrapper;
import exchange.component.wrapper.ConverterWrapperImpl;
import exchange.component.provider.RateProvider;
import exchange.component.provider.RateProviderImpl;
import exchange.model.Currency;

import static exchange.model.Color.*;

public class CurrencyExchangeDemo {
    public static void main(String[] args) {
        // exchangeFee и курсы валют поместил в файл src/main/resources/rate.json
        RateProvider rateProvider = new RateProviderImpl();
        ConverterWrapper converter = new ConverterWrapperImpl(rateProvider);
        UserInterface userInterface = new UserInterface();

        do {
            // Запрос выбора валюты у пользователя
            Currency currency = userInterface.chooseExchangeService();

            // Запрос ввода суммы для обмена у пользователя
            double amount = userInterface.enterAmount();

            // Выполнение конвертации валюты и получение результата
            String result = converter.convert(currency, amount, rateProvider.getFeeByCurrency(currency));

            // Вывод результата синим цветом
            System.out.println(BLUE + result + RESET);
            System.out.println();

            // выход из цикла по желанию пользователя
            System.out.print(GREEN + "Want to continue the exchange? (" + RED + "Y" + GREEN + "/" + RED + "N" + GREEN + ") " + RESET);
            String continueChoice = userInterface.scanner.nextLine();
            if (continueChoice.equalsIgnoreCase("N")) {
                System.out.println(YELLOW + "GOODBYE AND HAVE A GOOD DAY!" + RESET);
                break;
            }
        } while (true);


    }
}
