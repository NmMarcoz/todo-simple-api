package com.lucasangelo.todosimple.responses;

import com.lucasangelo.todosimple.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class UserResponse {
    private String message;
    private User user;
    private HttpStatus httpStatus;

    public UserResponse(String message){
        this.message = message;
    }
    public UserResponse(String message, User user){
        this.message = message;
        this.user = user;
    }
    public UserResponse(String message, User user, HttpStatus httpStatus){
        this.message = message;
        this.user = user;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
