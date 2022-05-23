package org.kaczucha.service;

public class IncorrectAmountException extends RuntimeException {
    public IncorrectAmountException(String message) {
        super(message);
    }
}
