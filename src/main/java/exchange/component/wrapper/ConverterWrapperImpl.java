package exchange.component.wrapper;

import exchange.component.converter.ConverterImpl;
import exchange.exception.UnsupportedCurrencyException;
import exchange.model.Currency;
import exchange.component.provider.RateProvider;

import static exchange.model.Color.BLUE;
import static exchange.model.Color.RESET;

public class ConverterWrapperImpl implements ConverterWrapper {
    private final ConverterImpl converterImpl;

    public ConverterWrapperImpl(RateProvider provider) {
        converterImpl = new ConverterImpl(provider);
    }

    public String convert(Currency currency, double amount, double fee) {
        switch (currency) {
            case EUR -> {
                double convertedAmount = converterImpl.eurToUsd(amount, fee);
                return BLUE + formatResult(amount) + " EUR = " + formatResult(convertedAmount) + " USD" + RESET;
            }
            case USD -> {
                double convertedAmount = converterImpl.usdToEur(amount, fee);
                return BLUE + formatResult(amount) + " USD = " + formatResult(convertedAmount) + " EUR" + RESET;
            }
            default -> {
                throw new UnsupportedCurrencyException("Unsupported currency " + currency.name());
            }
        }
    }

    // если после десятичной точки нет цифр кроме ноля, то метод удаляет ноль и точку,
    // в противном случае оставляет два знака после точки
    private String formatResult(double num) {
        String numStr = Double.toString(num);
        if (numStr.endsWith(".0")) return numStr.substring(0, numStr.length() - 2);
        return String.format("%.2f", num);
    }
}
