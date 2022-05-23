package org.kaczucha.service;

public class NoSufficientFundException extends RuntimeException {
    public NoSufficientFundException(String message) {
        super(message);
    }

}
