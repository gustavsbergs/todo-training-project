package com.bergsgustavs.todotrainingproject.data.mappers;

import com.bergsgustavs.todotrainingproject.data.domain.Task;
import com.bergsgustavs.todotrainingproject.data.dto.TaskDTO;
import org.springframework.stereotype.Component;

@Component
public class DTOMapper {

    public TaskDTO TaskToDTO(Task task){
        TaskDTO mappedTask = new TaskDTO();
        if(task.getId() != null){
            mappedTask.setId(task.getId());
        }
        if(task.getName() != null){
            mappedTask.setName(task.getName());
        }
        if(task.getDescription() != null){
            mappedTask.setDescription(task.getDescription());
        }
        if(task.getStartingDate() != null){
            mappedTask.setStartingDate(task.getStartingDate());
        }
        if(task.getEndingDate() != null){
            mappedTask.setEndingDate(task.getEndingDate());
        }
        if(task.getStartingTime() != null){
            mappedTask.setStartingTime(task.getStartingTime());
        }
        if(task.getEndingTime() != null){
            mappedTask.setEndingTime(task.getEndingTime());
        }
        return mappedTask;
    }

    public Task DTOToTask(TaskDTO task){
        Task mappedTask = new Task();
        if(task.getId() != null){
            mappedTask.setId(task.getId());
        }
        if(task.getName() != null){
            mappedTask.setName(task.getName());
        }
        if(task.getDescription() != null){
            mappedTask.setDescription(task.getDescription());
        }
        if(task.getStartingDate() != null){
            mappedTask.setStartingDate(task.getStartingDate());
        }
        if(task.getEndingDate() != null){
            mappedTask.setEndingDate(task.getEndingDate());
        }
        if(task.getStartingTime() != null){
            mappedTask.setStartingTime(task.getStartingTime());
        }
        if(task.getEndingTime() != null){
            mappedTask.setEndingTime(task.getEndingTime());
        }
        return mappedTask;
    }
}
