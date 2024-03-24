package exchange.component.converter;

import exchange.component.provider.RateProvider;
import exchange.component.provider.RateProviderImpl;
import exchange.component.wrapper.ConverterWrapperImpl;
import exchange.exception.NegativeIncomeException;
import org.junit.jupiter.api.Test;

import static exchange.model.Currency.EUR;
import static exchange.model.Currency.USD;
import static org.junit.jupiter.api.Assertions.*;

class ConverterImplTest {

    RateProvider rateProvider = new RateProviderImpl();
    ConverterImpl converter = new ConverterImpl(rateProvider);

    double amount;
    double exchangeFee;

    @Test
    void usdToEurTest() {

        amount = 100;
        exchangeFee = 0.05;

        Double expectedResult = 85.50;
        Double actualResult = converter.usdToEur(amount, exchangeFee);

        assertEquals(expectedResult, actualResult);

    }


    @Test
    void eurToUsdTest() {
        amount = 10;
        exchangeFee = 0.01;

        Double expectedResult = 10.89;
        Double actualResult = converter.eurToUsd(amount, exchangeFee);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void usdAmountBelowZeroIncome() {
        amount = -1;
        assertThrows(NegativeIncomeException.class, () -> {
            converter.usdToEur(amount, exchangeFee);
        });
    }

    @Test
    public void usdFeeBelowZeroIncome() {
        exchangeFee = -1;
        assertThrows(NegativeIncomeException.class, () -> {
            converter.usdToEur( amount, exchangeFee);
        });
    }

    @Test
    public void eurAmountBelowZeroIncome() {
        amount = -1;
        assertThrows(NegativeIncomeException.class, () -> {
            converter.eurToUsd(amount, exchangeFee);
        });
    }

    @Test
    public void eurFeeBelowZeroIncome() {
        exchangeFee = -1;
        assertThrows(NegativeIncomeException.class, () -> {
            converter.eurToUsd(amount, exchangeFee);
        });
    }
}