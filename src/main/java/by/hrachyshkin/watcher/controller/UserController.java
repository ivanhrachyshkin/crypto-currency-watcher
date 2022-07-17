package by.hrachyshkin.watcher.controller;

import by.hrachyshkin.watcher.dto.UserDto;
import by.hrachyshkin.watcher.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/notify", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final HateoasCreator hateoasCreator;
    private final UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody UserDto userDto, HttpSession session) {
        return userService.create(userDto);
    }

    @GetMapping(value = "/stop", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void stop() {
         userService.stopLogging();
    }
}
