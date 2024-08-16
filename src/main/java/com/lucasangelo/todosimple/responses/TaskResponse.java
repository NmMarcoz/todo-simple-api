package com.lucasangelo.todosimple.responses;

import com.lucasangelo.todosimple.models.Task;
import com.lucasangelo.todosimple.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;


public class TaskResponse {
    public String message;
    public HttpStatus httpStatus;

    public List<Task> tasks;

    public Task task;

    public User user;

    public TaskResponse(String message, HttpStatus httpStatus){
        this.message = message;
        this.httpStatus = httpStatus;


    }
    public TaskResponse(String message, HttpStatus httpStatus, Task task){
        this.message = message;
        this.httpStatus = httpStatus;
        this.task = task;

    }
    public TaskResponse(String message, HttpStatus httpStatus, Task task, List<Task> tasks){
        this.message = message;
        this.httpStatus = httpStatus;
        this.task = task;
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskResponse(String message, HttpStatus httpStatus, Task task, User user){
        this.message = message;
        this.httpStatus = httpStatus;
        this.task = task;
        this.user = user;
    }
    public TaskResponse(String message, HttpStatus httpStatus, Task task, User user, List<Task> tasks){
        this.message = message;
        this.httpStatus = httpStatus;
        this.task = task;
        this.user = user;
        this.tasks = tasks;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
