package com.mohammad.ExpenseManager.exception;

public class UsernameAlreadyExistsException extends RuntimeException{
    public  UsernameAlreadyExistsException (String message){
        super(message);
    }
}
