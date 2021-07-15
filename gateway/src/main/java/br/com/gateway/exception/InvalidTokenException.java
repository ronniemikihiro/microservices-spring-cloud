package br.com.gateway.exception;

public class InvalidTokenException extends Exception {

    public InvalidTokenException(String exception) {
        super(exception);
    }
}
