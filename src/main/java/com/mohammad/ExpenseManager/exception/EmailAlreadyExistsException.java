package com.mohammad.ExpenseManager.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException (String message){
        super(message);
    }
}
