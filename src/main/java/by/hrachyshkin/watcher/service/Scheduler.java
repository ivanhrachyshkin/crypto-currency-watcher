package by.hrachyshkin.watcher.service;

import by.hrachyshkin.watcher.config.Properties;
import by.hrachyshkin.watcher.model.Currency;
import by.hrachyshkin.watcher.model.Quote;
import by.hrachyshkin.watcher.repository.CurrencyRepository;
import by.hrachyshkin.watcher.repository.QuoteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Scheduler {

    private final RestTemplate restTemplate;
    private final Properties properties;
    private final ObjectMapper mapper;
    private final CurrencyRepository currencyRepository;
    private final QuoteRepository quoteRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Scheduled(fixedRate = 60000)
    public void track() throws JsonProcessingException {
        trackQuotes(trackCurrencyCatalog());
    }

    private List<Currency> trackCurrencyCatalog() throws JsonProcessingException {
        final String currencyCatalog = properties.getCatalog();
        final List<Currency> currencies = mapper.readValue(currencyCatalog, new TypeReference<List<Currency>>() {
        });

        return currencyRepository.count() != currencies.size()
                ? currencies
                : currencyRepository.saveAll(currencies);
    }

    private void trackQuotes(final List<Currency> currencies) throws JsonProcessingException {
        for (Currency currency : currencies) {
            final ResponseEntity<String> response
                    = restTemplate.getForEntity(properties.getUrl().concat(currency.getId().toString()), String.class);
            final List<Quote> quotes = mapper.readValue(response.getBody(), new TypeReference<List<Quote>>() {
            });
            quoteRepository.save(quotes.get(0));
        }
        this.eventPublisher.publishEvent(new QuotesEvent());
    }
}
