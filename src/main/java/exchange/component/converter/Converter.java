package exchange.component.converter;

import exchange.model.Currency;

import java.util.Map;

public interface Converter {

    double usdToEur(double amount, double exchangeFee);

    double eurToUsd(double amount, double exchangeFee);
}
