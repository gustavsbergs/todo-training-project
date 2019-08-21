package com.bergsgustavs.todotrainingproject.data.mappers;

import com.bergsgustavs.todotrainingproject.data.domain.Task;
import com.bergsgustavs.todotrainingproject.data.dto.TaskDTO;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class DTOMapper {

    final private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public TaskDTO TaskToDTO(final Task task) {
        final TaskDTO mappedTask = new TaskDTO();
        mappedTask.setId(task.getId());
        mappedTask.setName(task.getName());
        mappedTask.setDescription(task.getDescription());
        mappedTask.setStartingDate(simpleDateFormat.format(task.getStartingDate()));
        mappedTask.setEndingDate(simpleDateFormat.format(task.getEndingDate()));
        return mappedTask;
    }

    public Task DTOToTask(final TaskDTO task) throws ParseException {
        final Task mappedTask = new Task();
        mappedTask.setId(task.getId());
        mappedTask.setName(task.getName());
        mappedTask.setDescription(task.getDescription());
        mappedTask.setStartingDate(simpleDateFormat.parse(task.getStartingDate()));
        mappedTask.setEndingDate(simpleDateFormat.parse(task.getEndingDate()));
        return mappedTask;
    }
}
