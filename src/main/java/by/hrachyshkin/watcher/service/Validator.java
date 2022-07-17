package by.hrachyshkin.watcher.service;

import by.hrachyshkin.watcher.config.Properties;
import by.hrachyshkin.watcher.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Validator {

    private final Properties properties;

    public void intValidate(final Integer id) {
        if (id == null || id < 1) {
            throw new ServiceException(properties.getNegative(), HttpStatus.BAD_REQUEST, id);
        }
    }

    public void longValidate(final Long id) {
        if (id == null || id < 1) {
            throw new ServiceException(properties.getNegative(), HttpStatus.BAD_REQUEST, id);
        }
    }

    public void userValidate(final UserDto userDto) {
        if (ObjectUtils.anyNotNull(userDto.getId(), userDto.getPrice())) {
            throw new ServiceException(properties.getUserInvalid(), HttpStatus.BAD_REQUEST);
        }

        if (!ObjectUtils.isNotEmpty(userDto.getCurrencyId()) || !ObjectUtils.isNotEmpty(userDto.getName())) {
            throw new ServiceException(properties.getUserInvalid(), HttpStatus.BAD_REQUEST);
        }
    }
}
