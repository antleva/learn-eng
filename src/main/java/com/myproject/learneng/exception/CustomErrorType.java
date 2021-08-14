package com.myproject.learneng.exception;

public class CustomErrorType extends RuntimeException {
    private String errorMessage;
    public CustomErrorType(String errorMessage){
        super(errorMessage);
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}