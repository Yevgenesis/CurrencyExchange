package exchange.component.wrapper;

import exchange.component.provider.RateProvider;
import exchange.component.provider.RateProviderImpl;
import exchange.exception.NegativeIncomeException;
import org.junit.jupiter.api.Test;

import static exchange.model.Currency.EUR;
import static exchange.model.Currency.USD;
import static org.junit.jupiter.api.Assertions.*;

class ConverterWrapperImplTest {

    RateProvider rateProvider = new RateProviderImpl();
    ConverterWrapperImpl converter = new ConverterWrapperImpl(rateProvider);

    @Test
    void convertUsdTest() {
        double amount = 100;
        double exchangeFee = 0.05;

        String expectedResult = "100 USD = 85.50 EUR";
        String actualResult = converter.convert(USD, amount, exchangeFee);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void convertEurTest() {
        double amount = 10;
        double exchangeFee = 0.01;

        String expectedResult = "10 EUR = 10.89 USD";
        String actualResult = converter.convert(EUR, amount, exchangeFee);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void convertUsdNegativeAmount() {
        double amount = -100;
        double exchangeFee = 0.05;

        assertThrows(NegativeIncomeException.class, () -> converter.convert(USD, amount, exchangeFee));
    }

    @Test
    void convertUsdNegativeFee() {
        double amount = 100;
        double exchangeFee = -0.05;

        assertThrows(NegativeIncomeException.class, () -> converter.convert(USD, amount, exchangeFee));
    }

    @Test
    void convertEurNegativeAmount() {
        double amount = -10;
        double exchangeFee = 0.01;

        assertThrows(NegativeIncomeException.class, () -> converter.convert(EUR, amount, exchangeFee));
    }

    @Test
    void convertEurNegativeFee() {
        double amount = 10;
        double exchangeFee = -0.01;

        assertThrows(NegativeIncomeException.class, () -> converter.convert(EUR, amount, exchangeFee));
    }
}