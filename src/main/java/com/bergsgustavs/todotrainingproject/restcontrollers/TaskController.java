package com.bergsgustavs.todotrainingproject.restcontrollers;

import com.bergsgustavs.todotrainingproject.exceptions.TaskNotFoundException;
import com.bergsgustavs.todotrainingproject.data.domain.Task;
import com.bergsgustavs.todotrainingproject.data.dto.TaskDTO;
import com.bergsgustavs.todotrainingproject.data.mappers.DTOMapper;
import com.bergsgustavs.todotrainingproject.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TaskController {

    private TaskService taskService;

    private DTOMapper dtoMapper;

    public TaskController(final TaskService taskService, final DTOMapper dtoMapper) {
        this.taskService = taskService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping(value = "/tasks/{id}", produces = "application/json")
    public TaskDTO getTask(@PathVariable final Long id) throws TaskNotFoundException {
        final Task returnTask = taskService.getTask(id);
        return dtoMapper.TaskToDTO(returnTask);
    }

    @PostMapping(value = "/tasks/new", produces = "application/json", consumes = "application/json")
    public TaskDTO createTask(@RequestBody final TaskDTO taskParams) throws Exception {
        final Task newTask = taskService.createTask(dtoMapper.DTOToTask(taskParams));
        return dtoMapper.TaskToDTO(newTask);
    }

    @PutMapping(value = "/tasks/update/{id}", produces = "application/json", consumes = "application/json")
    public TaskDTO updateTask(@RequestBody final TaskDTO taskParams, @PathVariable Long id) throws TaskNotFoundException, ParseException {
        final Task updatedTask = taskService.updateTask(id, dtoMapper.DTOToTask(taskParams));
        return dtoMapper.TaskToDTO(updatedTask);
    }

    @DeleteMapping(value = "/tasks/delete/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteTask(@PathVariable final Long id) throws TaskNotFoundException {
        taskService.deleteTask(id);
    }

    @GetMapping(value = "/tasks", produces = "application/json")
    public List<TaskDTO> getAllTasks(){
        final List<Task> listOfTasks = taskService.returnAll();
        return listOfTasks.stream()
                .map(dtoMapper::TaskToDTO)
                .collect(Collectors.toList());
    }
}
