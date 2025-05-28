package com.mohammad.ExpenseManager.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException (String message){
        super(message);
    }
}