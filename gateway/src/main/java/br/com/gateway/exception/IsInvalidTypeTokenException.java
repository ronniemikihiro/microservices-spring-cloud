package br.com.gateway.exception;

public class IsInvalidTypeTokenException extends Exception {

    public IsInvalidTypeTokenException() {
        super();
    }

    public IsInvalidTypeTokenException(String exception) {
        super(exception);
    }
}
