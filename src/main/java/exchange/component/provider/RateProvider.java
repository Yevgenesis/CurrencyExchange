package exchange.component.provider;

import exchange.model.Currency;

import java.util.Map;

public interface RateProvider {
    Map<String, Double> getRatesByCurrency(Currency currency);

     double getFeeByCurrency(Currency currency);


    }
