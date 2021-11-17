package br.com.domain.exception.errors;

import org.slf4j.Logger;

public class ErrorException extends Exception {

    public ErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorException(Logger log, String message) {
        super(message);
        log.error(message);
    }

    public ErrorException(Logger log, String message, Throwable cause) {
        super(message, cause);
        log.error(message);
    }

}
