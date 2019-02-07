package com.bergsgustavs.todotrainingproject.services;

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

    /** Returns tasks by id.
     *
     * @param taskId
     * @return task
     */
    public Task getTask(final Long taskId) throws Exception {
        return taskRepository.findById(taskId).orElseThrow(() ->new Exception("Task not found!"));
    }

    /** Creates new tasks using given values
     *
     * @param newTask
     * @return task
     */
    public Task createTask(final Task newTask){
        return taskRepository.save(newTask);
    }

    /**
     * Updates task from data object using id
     * @param id
     * @param task
     * @return Task
     * @throws Exception
     */
    public Task updateTask(final Long id, final Task task) throws Exception {

        final Task taskToUpdate = taskRepository.getOne(id);

        if(taskToUpdate == null){
            throw new Exception("Task not found!");
        }
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setName(task.getName());
        taskToUpdate.setStartingDate(task.getStartingDate());
        taskToUpdate.setEndingDate(task.getEndingDate());

        createTask(taskToUpdate);

        return taskRepository.findById(id).orElseThrow(()->new Exception("Task not found!"));
    }

    /**
     * Delete task by id.
     * @param id
     * @throws Exception
     */
    public void deleteTask(final Long id) throws Exception {
        taskRepository.findById(id).orElseThrow(() -> new Exception("Task not found or already deleted!"));
        taskRepository.deleteById(id);
    }

    /**
     * Method that returns an ArrayList of all tasks!
     */
    public List<Task> returnAll () {
        final List<Task> allTasks = taskRepository.findAll();
        Collections.sort(allTasks);
        return allTasks;
    }

}
