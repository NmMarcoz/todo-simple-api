package com.lucasangelo.todosimple.handlers;

import org.springframework.stereotype.Component;


public class ApiError {
    String message;
    public ApiError(String message) {
        this.message = message;

    }
}
