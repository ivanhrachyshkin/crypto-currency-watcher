package by.hrachyshkin.watcher.controller;

import lombok.Data;

@Data
public class ApiError {

    private final int code;
    private final String message;
}
