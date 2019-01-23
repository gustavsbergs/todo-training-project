package com.bergsgustavs.todotrainingproject.data.mappers;

import com.bergsgustavs.todotrainingproject.data.domain.Task;
import com.bergsgustavs.todotrainingproject.data.dto.TaskDTO;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class DTOMapper {

	public TaskDTO TaskToDTO(Task task){
		final TaskDTO mappedTask = new TaskDTO();

		if (task.getId() != null) {
			mappedTask.setId(task.getId());
		}
		if (task.getName() != null) {
			mappedTask.setName(task.getName());
		}
		if (task.getDescription() != null) {
			mappedTask.setDescription(task.getDescription());
		}
		if (task.getStartingDate() != null) {
			mappedTask.setStartingDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(task.getStartingDate()));
		}
		if (task.getEndingDate() != null) {
			mappedTask.setEndingDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(task.getEndingDate()));
		}
		return mappedTask;
	}

	public Task DTOToTask(TaskDTO task) throws ParseException {
		final Task mappedTask = new Task();

		if (task.getId() != null) {
			mappedTask.setId(task.getId());
		}
		if (task.getName() != null) {
			mappedTask.setName(task.getName());
		}
		if (task.getDescription() != null) {
			mappedTask.setDescription(task.getDescription());
		}
		if (task.getStartingDate() != null) {
			mappedTask.setStartingDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(task.getStartingDate()));
		}
		if (task.getEndingDate() != null) {
			mappedTask.setEndingDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(task.getEndingDate()));
		}
		return mappedTask;
	}
}
