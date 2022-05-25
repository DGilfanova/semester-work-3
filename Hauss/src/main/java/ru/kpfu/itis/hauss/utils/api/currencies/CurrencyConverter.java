package ru.kpfu.itis.hauss.utils.api.currencies;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CurrencyConverter {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyConverter.class);

    private String currencyApiUrl;
    private final String personalKey;

    private final static String ACTION_PARAMETER_NAME = "get";
    private final static String GET_ALL_CURRENCY_PARAMETER_VALUE = "currency_list";
    private final static String GET_CURRENCY_PARAMETER_VALUE = "rates";
    private final static String CODE_PARAMETER_NAME = "pairs";
    private final static String PERSONAL_KEY_PARAMETER = "key";

    private final ObjectMapper objectMapper;
    private final OkHttpClient okHttpClient;

    private final List<String> validCurrencies;

    @Autowired
    public CurrencyConverter(ObjectMapper mapper, OkHttpClient client,
                             @Value("${currency_api_personal_key}") String personalKey,
                             @Value("${currency_api_url}") String currencyApiUrl) {
        this.objectMapper = mapper;
        this.okHttpClient = client;
        this.personalKey = personalKey;
        this.currencyApiUrl = currencyApiUrl;
        this.validCurrencies = getApiCurrencyCodes();

        logger.info("Successfully init currency list");
    }

    public Double convert(String from, String to, Double value) {

        String code = to + from;
        String reverseCode = from + to;

        if (!validCurrencies.contains(code) && !validCurrencies.contains(reverseCode)) {
            throw new CurrencyConverterException("Invalid convert types");
        }

        HttpUrl.Builder urlBuilder
                = Objects.requireNonNull(HttpUrl.parse(currencyApiUrl)).newBuilder();
        urlBuilder.addQueryParameter(ACTION_PARAMETER_NAME, GET_CURRENCY_PARAMETER_VALUE);
        urlBuilder.addQueryParameter(CODE_PARAMETER_NAME, code);
        urlBuilder.addQueryParameter(PERSONAL_KEY_PARAMETER, personalKey);

        String url = urlBuilder.build().toString();
        System.out.println(url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = okHttpClient.newCall(request);

        try {
            Response response = call.execute();

            logger.info("Successfully execution of a request to get the currency rates for " + code);

            JsonNode node = objectMapper.readTree(response.body().bytes());
            Double course = node.get("data").get(code).asDouble();
            Double newPrice = course*value;
            return Double.parseDouble(String.format("%.2f", newPrice).replace(",", "."));
        } catch (IOException e) {
            logger.error("Failed to execute the request to get the currency rates. Problem with server");

            throw new CurrencyConverterException("Problem with converter server. Please try later", e);
        }
    }

    public List<String> getApiCurrencyCodes() {
        HttpUrl.Builder urlBuilder
                = HttpUrl.parse(currencyApiUrl).newBuilder();
        urlBuilder.addQueryParameter(ACTION_PARAMETER_NAME, GET_ALL_CURRENCY_PARAMETER_VALUE);
        urlBuilder.addQueryParameter(PERSONAL_KEY_PARAMETER, personalKey);

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = okHttpClient.newCall(request);

        List<String> currencies = new ArrayList<>();
        try {
            Response response = call.execute();

            logger.info("Successfully execution of a request to get all currency rates");

            JsonNode node = objectMapper.readTree(response.body().bytes()).get("data");
            for (JsonNode nod : node) {
                currencies.add(nod.asText());
            }
            return currencies;
        } catch (IOException e) {
            logger.error("Failed to execute the request to get all currency rates");

            throw new CurrencyConverterException("Problem with converter server.", e);
        }
    }
}
