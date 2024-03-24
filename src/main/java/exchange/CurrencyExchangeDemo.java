package exchange;

import exchange.component.service.UserInterface;
import exchange.component.wrapper.ConverterWrapperImpl;
import exchange.component.provider.RateProvider;
import exchange.component.provider.RateProviderImpl;
import exchange.model.Currency;

import static exchange.model.Color.*;

public class CurrencyExchangeDemo {
    public static void main(String[] args) {
        // exchangeFee и курсы валют поместил в файл src/main/resources/rate.json
        RateProvider rateProvider = new RateProviderImpl();
        ConverterWrapperImpl converter = new ConverterWrapperImpl(rateProvider);
        UserInterface userInterface = new UserInterface();

        do {
            Currency currency = userInterface.chooseExchangeService();
            double amount = userInterface.enterAmount();
            String result = converter.convert(currency, amount, rateProvider.getFeeByCurrency(currency));

            System.out.println(BLUE + result + RESET);
            System.out.println();

            // выход из цикла по желанию пользователя
            System.out.print(GREEN + "Want to continue the exchange? (" + RED + "Y" + GREEN + "/" + RED + "N" + GREEN + ") " + RESET);
            String continueChoice = userInterface.scanner.nextLine();
            if (continueChoice.equalsIgnoreCase("N")) {
                System.out.println(YELLOW + "Goodbye and have a good day!" + RESET);
                break;
            }
        } while (true);


    }
}
