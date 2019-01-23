package com.bergsgustavs.todotrainingproject.restcontrollers;

import com.bergsgustavs.todotrainingproject.data.domain.Task;
import com.bergsgustavs.todotrainingproject.data.dto.TaskDTO;
import com.bergsgustavs.todotrainingproject.data.mappers.DTOMapper;
import com.bergsgustavs.todotrainingproject.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public TaskDTO getTask(@PathVariable Long id) throws Exception {

        Task returnTask = taskService.getTask(id);

        return dtoMapper.TaskToDTO(returnTask);
    }

    @PostMapping(value = "/tasks/new", produces = "application/json", consumes = "application/json")
    public TaskDTO createTask(@RequestBody TaskDTO taskParams) throws Exception {

        Task newTask = taskService.createTask(dtoMapper.DTOToTask(taskParams));

        return dtoMapper.TaskToDTO(newTask);
    }

    @PutMapping(value = "/tasks/update/{id}", produces = "application/json" , consumes = "application/json")
    public TaskDTO updateTask(@RequestBody TaskDTO taskParams, @PathVariable Long id) throws Exception {

        Task updatedTask = taskService.updateTask(id, dtoMapper.DTOToTask(taskParams));

        return dtoMapper.TaskToDTO(updatedTask);
    }

    @DeleteMapping(value = "/tasks/delete/{id}", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteTask(@PathVariable Long id) throws Exception {

        taskService.deleteTask(id);
    }

    @GetMapping(value = "/tasks", produces = "application/json")
    public List<TaskDTO> getAllTasks() throws Exception {
        final List<Task> listOfTasks = taskService.returnAll();

        return listOfTasks.stream()
                .map(dtoMapper::TaskToDTO)
                .collect(Collectors.toList());
    }
}
