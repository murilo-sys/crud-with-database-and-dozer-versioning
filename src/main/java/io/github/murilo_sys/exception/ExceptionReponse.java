package io.github.murilo_sys.exception;

import java.util.Date;

public record ExceptionReponse(Date timestamp, String errorcode, String message, String details) {}
