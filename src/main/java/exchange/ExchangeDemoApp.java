package exchange;

import exchange.component.service.UserInterface;
import exchange.component.wrapper.ConverterWrapperImpl;
import exchange.component.provider.RateProvider;
import exchange.component.provider.RateProviderImpl;
import exchange.model.Currency;

import static exchange.model.Color.*;
import static exchange.model.Currency.EUR;
import static exchange.model.Currency.USD;

public class ExchangeDemoApp {
    /*
Задача:

  Develop a program for converting USD to EUR and EUR to USD.
  The program shall include 2 classes: Converter and ConverterWrapper.
1. Class Converter shall contain methods for conversion USD to EUR and EUR to USD.
    The arguments of methods are an amount for the conversion and an exchange fee.
    The rate could be received by calling the method of RateProvider.
    There are no requirements for RateProvider implementation.
2. Class ConverterWrapper shall contain a method “convert”.
    The arguments of the method are an amount for the conversion,
    convertible currency symbol (USD or EUR) and an exchange fee.
    This method shall call the required method from class Converter and
    shall return the result in the following format:
    ".. USD = ,, EUR" or ".. EUR = ,, USD" where .. is input amount;  is conversion result.
3. The program shall be tested by unit tests.

Example 1:
Amount: 100
Rate: 0.9
Currency: USD
Fee: 0.05
Expected result: 100 USD = 85.5 EUR

Example 2:
Amount: 10
Rate: 1.1
Currency: EUR
Fee: 0.01
Expected result: 10 EUR = 10.89 USD
*/
    public static void main(String[] args) {
        RateProvider rateProvider = new RateProviderImpl();
        ConverterWrapperImpl converter = new ConverterWrapperImpl(rateProvider);
        UserInterface userInterface = new UserInterface();


        do {
            Currency currency = userInterface.chooseExchangeService();
            double amount = userInterface.enterAmount();

            // exchangeFee и курсы валют поместил в файл src/main/resources/rate.json
            String result = converter.convert(currency, amount, rateProvider.getFeeByCurrency(currency));

            System.out.println(result);
            System.out.println();

            // выход из цикла по желанию пользователя
            System.out.println(GREEN + "Want to continue conversation? (" + RED + "Y" + GREEN + "/" + RED + "N" + GREEN + ")" + RESET);
            String continueChoice = userInterface.scanner.nextLine();
            if (continueChoice.equalsIgnoreCase("N")) {
                System.out.println(GREEN + "Goodbye and have a good day!" + RESET);
                break;
            }
        } while (true);


    }
}
