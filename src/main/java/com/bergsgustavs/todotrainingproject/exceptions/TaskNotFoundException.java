package com.bergsgustavs.todotrainingproject.exceptions;

public class TaskNotFoundException extends Exception {
    public TaskNotFoundException(final String errorMessage){
        super(errorMessage);
    }
}
