package by.hrachyshkin.watcher.service;

import by.hrachyshkin.watcher.dto.UserDto;

public interface UserService {

    UserDto create(final UserDto userDto);

    public void stopLogging();
}
