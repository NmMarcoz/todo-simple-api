package com.lucasangelo.todosimple.handlers;

import org.springframework.stereotype.Component;

public class ApiError {
    String message;
    String title;

    public ApiError(String message, String title) {
        this.message = message;
        this.title = title;
    }
}
