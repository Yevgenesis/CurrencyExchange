package exchange.component.converter;

import exchange.component.provider.RateProvider;
import exchange.component.provider.RateProviderImpl;
import exchange.exception.NegativeIncomeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConverterImplTest {

    RateProvider rateProvider = new RateProviderImpl();
    ConverterImpl converter = new ConverterImpl(rateProvider);


    @Test
    void usdToEurTest() {
        double amount = 100;
        double exchangeFee = 0.05;

        double expectedResult = 85.5;
        double actualResult = converter.usdToEur(amount, exchangeFee);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void eurToUsdTest() {
        double amount = 10;
        double exchangeFee = 0.01;

        double expectedResult = 10.89;
        double actualResult = converter.eurToUsd(amount, exchangeFee);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void usdAmountBelowZeroIncome() {
        double amount = -1;
        assertThrows(NegativeIncomeException.class, () -> converter.usdToEur(amount, 0.01));
    }

    @Test
    public void usdFeeBelowZeroIncome() {
        double exchangeFee = -1;
        assertThrows(NegativeIncomeException.class, () -> converter.usdToEur(10, exchangeFee));
    }

    @Test
    public void eurAmountBelowZeroIncome() {
        double amount = -1;
        assertThrows(NegativeIncomeException.class, () -> converter.eurToUsd(amount, 0.01));
    }

    @Test
    public void eurFeeBelowZeroIncome() {
        double exchangeFee = -1;
        assertThrows(NegativeIncomeException.class, () -> converter.eurToUsd(10, exchangeFee));
    }

}