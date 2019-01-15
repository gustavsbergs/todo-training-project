package com.bergsgustavs.todotrainingproject.restcontrollers;

import com.bergsgustavs.todotrainingproject.data.domain.Task;
import com.bergsgustavs.todotrainingproject.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping(value = "/task/get/{id}", produces = "application/json")
    public Task getTask(@PathVariable Long id) throws Exception {
        Task returnTask = taskService.getTask(id);


        return returnTask;
    }

    @PostMapping(value = "/task/create", produces = "application/json")
    public Task createTask(@RequestBody Task taskParams){
        return taskService.createTask(taskParams.getName(), taskParams.getDescription());
    }

    @PostMapping(value = "/task/update/{id}", produces = "application/json")
    public Task updateTask(@RequestBody Task taskParams, @PathVariable Long id) throws Exception {
        Task updatedTask = taskService.updateTask(id, taskParams.getName(), taskParams.getDescription());
        return updatedTask;
    }
}