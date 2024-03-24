package exchange.component.converter;

import exchange.exception.NegativeIncomeException;
import exchange.model.Currency;
import exchange.component.provider.RateProvider;

import java.util.Map;

import static exchange.model.Color.RED;
import static exchange.model.Color.RESET;
import static exchange.model.Currency.EUR;
import static exchange.model.Currency.USD;

public class ConverterImpl {
    private final RateProvider rateProvider;

    public ConverterImpl(RateProvider rateProvider) {
        this.rateProvider = rateProvider;
    }

    public double usdToEur(double amount, double exchangeFee) {
        return convertAmount(USD, EUR, amount, exchangeFee);
    }

    public double eurToUsd(double amount, double exchangeFee) {
        return convertAmount(EUR, USD, amount, exchangeFee);

    }

    private double convertAmount(Currency fromCurrency, Currency toCurrency, double amount, double exchangeFee) {
        if (amount < 0 || exchangeFee < 0) {
            throw new NegativeIncomeException(RED+"The amount or fee must be positive!"+RESET);
        }
        Map<String, Double> convertedAmount = rateProvider.getRatesByCurrency(fromCurrency);
        return (convertedAmount.get(toCurrency.name()) * amount) * (1 - exchangeFee);
    }


}
