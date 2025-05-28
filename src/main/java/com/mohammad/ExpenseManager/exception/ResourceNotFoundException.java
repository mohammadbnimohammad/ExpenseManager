package com.mohammad.ExpenseManager.exception;

public class ResourceNotFoundException extends  RuntimeException {
    public ResourceNotFoundException (String message){
        super(message);
    }
}
