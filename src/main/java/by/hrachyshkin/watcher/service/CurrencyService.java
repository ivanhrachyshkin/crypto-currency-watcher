package by.hrachyshkin.watcher.service;

import by.hrachyshkin.watcher.dto.CurrencyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CurrencyService {

    Page<CurrencyDto> readAll(final Pageable pageable);

    CurrencyDto readOne(final int id) throws ServiceException;
}
