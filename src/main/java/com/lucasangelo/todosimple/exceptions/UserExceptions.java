package com.lucasangelo.todosimple.exceptions;

import org.springframework.http.HttpStatus;

public class UserExceptions extends RuntimeException{
    private String message;

    private HttpStatus statusCode;

    public UserExceptions(String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
