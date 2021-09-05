package com.xbky.circuitManger.utils;

public class ExcelException extends RuntimeException {

    private String message;

    public ExcelException() {
        super();
    }

    public ExcelException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
