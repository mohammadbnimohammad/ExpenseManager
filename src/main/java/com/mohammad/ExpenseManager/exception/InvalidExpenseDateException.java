package com.mohammad.ExpenseManager.exception;

public class InvalidExpenseDateException extends RuntimeException {
    public InvalidExpenseDateException(String message) {
        super(message);
    }
}
