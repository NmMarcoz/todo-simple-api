package com.lucasangelo.todosimple.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class TaskExceptions extends RuntimeException {
    private String message;
    private HttpStatus statusCode;

    public TaskExceptions(){

    }
    public TaskExceptions(String message, HttpStatus statusCode){
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
