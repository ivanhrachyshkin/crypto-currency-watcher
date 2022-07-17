package by.hrachyshkin.watcher.service;

import by.hrachyshkin.watcher.config.Properties;
import by.hrachyshkin.watcher.dto.CurrencyDto;
import by.hrachyshkin.watcher.dto.DtoMapper;
import by.hrachyshkin.watcher.model.Currency;
import by.hrachyshkin.watcher.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImp implements CurrencyService {

    private final Properties properties;
    private final Validator validator;
    private final DtoMapper<Currency, CurrencyDto> dtoMapper;
    private final CurrencyRepository currencyRepository;

    @Override
    public Page<CurrencyDto> readAll(final Pageable pageable) {
        return dtoMapper.modelsToDto(currencyRepository.findAll(pageable));
    }

    @Override
    public CurrencyDto readOne(final int id) throws ServiceException {
        validator.intValidate(id);
        final Currency currency = currencyRepository
                .findById(id)
                .orElseThrow(() -> new ServiceException(properties.getNotFound(),
                        HttpStatus.NOT_FOUND, "currency", id));

        return dtoMapper.modelToDto(currency);
    }
}
