package com.bergsgustavs.todotrainingproject.services;

import com.bergsgustavs.todotrainingproject.exceptions.TaskNotFoundException;
import com.bergsgustavs.todotrainingproject.data.domain.Task;
import com.bergsgustavs.todotrainingproject.data.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task getTask(final Long taskId) throws TaskNotFoundException {
        return taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task with id " + taskId + " not found"));
    }

    public Task createTask(final Task newTask) {
        return taskRepository.save(newTask);
    }

    public Task updateTask(final Long id, final Task task) throws TaskNotFoundException {
        final Task taskToUpdate = taskRepository.getOne(id);
        if (taskToUpdate == null) {
            throw new TaskNotFoundException("Task with id " + id + " not found!");
        }
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setName(task.getName());
        taskToUpdate.setStartingDate(task.getStartingDate());
        taskToUpdate.setEndingDate(task.getEndingDate());

        createTask(taskToUpdate);

        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task with id: " + id + " not found!"));
    }

    public void deleteTask(final Long id) throws TaskNotFoundException {
        taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found or already deleted!"));
        taskRepository.deleteById(id);
    }

    public List<Task> returnAll() {
        final List<Task> allTasks = taskRepository.findAll();
        Collections.sort(allTasks);
        return allTasks;
    }

}
