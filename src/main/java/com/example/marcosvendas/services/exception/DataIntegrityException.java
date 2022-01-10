package com.example.marcosvendas.services.exception;

public class DataIntegrityException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public DataIntegrityException(String msg) {
        super(msg);
    }

    public DataIntegrityException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
