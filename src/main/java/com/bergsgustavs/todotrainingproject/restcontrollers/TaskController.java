package com.bergsgustavs.todotrainingproject.restcontrollers;

import com.bergsgustavs.todotrainingproject.data.domain.Task;
import com.bergsgustavs.todotrainingproject.data.dto.TaskDTO;
import com.bergsgustavs.todotrainingproject.data.mappers.DTOMapper;
import com.bergsgustavs.todotrainingproject.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    DTOMapper dtoMapper;

    @GetMapping(value = "/task/get/{id}", produces = "application/json")
    public TaskDTO getTask(@PathVariable Long id) throws Exception {

        Task returnTask = taskService.getTask(id);

        return dtoMapper.TaskToDTO(returnTask);
    }

    @PostMapping(value = "/task/create", produces = "application/json")
    public TaskDTO createTask(@RequestBody TaskDTO taskParams){
        Task newTask = taskService.createTask(dtoMapper.DTOToTask(taskParams));

        return dtoMapper.TaskToDTO(newTask);
    }

    @PostMapping(value = "/task/update/{id}", produces = "application/json")
    public TaskDTO updateTask(@RequestBody TaskDTO taskParams, @PathVariable Long id) throws Exception {

        Task updatedTask = taskService.updateTask(id, taskParams.getName(), taskParams.getDescription());

        return dtoMapper.TaskToDTO(updatedTask);
    }

    @GetMapping(value = "/task/delete/{id}", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteTask(@PathVariable Long id) throws Exception{

        taskService.deleteTask(id);
    }
}
