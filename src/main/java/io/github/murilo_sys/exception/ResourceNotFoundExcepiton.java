package io.github.murilo_sys.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundExcepiton extends RuntimeException {
    public ResourceNotFoundExcepiton(String message) {
        super(message);
    }
}
