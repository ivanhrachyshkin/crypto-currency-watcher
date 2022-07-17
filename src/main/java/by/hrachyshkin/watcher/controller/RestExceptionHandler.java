package by.hrachyshkin.watcher.controller;

import by.hrachyshkin.watcher.service.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ApiError> handleServiceException(final ServiceException e) {
        final HttpStatus status = e.getStatus();
        final ApiError apiError = new ApiError(status.value(), e.getMessage());
        return new ResponseEntity<>(apiError, status);
    }
}
