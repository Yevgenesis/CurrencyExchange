package exchange.component.provider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exchange.model.Currency;


public class RateProviderImpl implements RateProvider {

    // Путь к файлу с курсами валют
    private static final String FILE_NAME = "/rate.json";
    private final ObjectMapper objectMapper;

    public RateProviderImpl() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Map<String, Double> getRatesByCurrency(Currency currency) {
        // Получение курсов валют из файла
        Map<String, Object> ratesMap = getRatesFromFile();

        // Приведение типа к Map<String, Double> и возвращение
        return (Map<String, Double>) ratesMap.get(currency.name());
    }



    @Override
    public double getFeeByCurrency(Currency currency){
        // Получаем комиссию (Exchange fee) из файла для заданной валюты
        Map<String, Double> ratesMap = getRatesByCurrency(currency);
        return ratesMap.get("EXCHANGE_FEE");

    }

    private Map<String, Object> getRatesFromFile() {
        try (InputStream inputStream = getClass().getResourceAsStream(FILE_NAME)) {
            // Проверка наличия файла
            if (inputStream == null) {
                throw new FileNotFoundException("File " + FILE_NAME + " not found!");
            }
            // Чтение файла и преобразование JSON в объект Map
            return objectMapper.readValue(inputStream, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to read or parse the file " + FILE_NAME, e);
        }
    }

}
