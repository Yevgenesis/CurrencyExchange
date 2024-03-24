package exchange.component.provider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exchange.model.Currency;


public class RateProviderImpl implements RateProvider {

    private static final String FILE_NAME = "/rate.json";
    private final ObjectMapper objectMapper;

    public RateProviderImpl() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Map<String, Double> getRatesByCurrency(Currency currency) {
        Map<String, Object> ratesMap = getRatesFromFile();
        return (Map<String, Double>) ratesMap.get(currency.name());
    }

    @Override
    public double getFeeByCurrency(Currency currency){
        Map<String, Double> ratesMap = getRatesByCurrency(currency);
        return ratesMap.get("EXCHANGE_FEE");

    }

    private Map<String, Object> getRatesFromFile() {
        try (InputStream inputStream = getClass().getResourceAsStream(FILE_NAME)) {
            if (inputStream == null) {
                throw new FileNotFoundException("File " + FILE_NAME + " not found!");
            }
            return objectMapper.readValue(inputStream, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to read or parse the file " + FILE_NAME, e);
        }
    }

}
