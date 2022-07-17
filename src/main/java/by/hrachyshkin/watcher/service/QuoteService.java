package by.hrachyshkin.watcher.service;

import by.hrachyshkin.watcher.dto.QuoteDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuoteService {

    Page<QuoteDto> readAll(final Pageable pageable);

    QuoteDto readOne(final long id) throws ServiceException;

    QuoteDto readTopOneByCurrency(final int id) throws ServiceException;
}
