package exchange.component.wrapper;

import exchange.model.Currency;

public interface ConverterWrapper {

    String convert(Currency currency, double amount, double fee);

}
