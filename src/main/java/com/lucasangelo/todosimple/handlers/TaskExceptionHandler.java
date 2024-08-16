package com.lucasangelo.todosimple.handlers;

import com.lucasangelo.todosimple.exceptions.TaskExceptions;
import com.lucasangelo.todosimple.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class TaskExceptionHandler extends ResponseEntityExceptionHandler {
    private String message;
    private HttpStatus status;
    @Autowired
    private TaskExceptions taskExceptions;
    private Task task;
    public TaskExceptionHandler(TaskExceptions taskExceptions){
        this.taskExceptions = taskExceptions;
    }

    @ExceptionHandler(TaskExceptions.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity invalidFieldException(TaskExceptions exception){
        //return ResponseEntityExceptionHandler()
        return handleExceptionInternal(exception, exception.getMessage(), null, exception.getStatusCode(), null);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public TaskExceptions getTaskExceptions() {
        return taskExceptions;
    }

    public void setTaskExceptions(TaskExceptions taskExceptions) {
        this.taskExceptions = taskExceptions;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
