package com.bergsgustavs.todotrainingproject.services;

import com.bergsgustavs.todotrainingproject.data.domain.Task;
import com.bergsgustavs.todotrainingproject.data.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;


    /** Returns tasks by id.
     *
     * @param taskId
     * @return task
     */
    public Task getTask(Long taskId) throws Exception {

        Task returnTask = taskRepository.findById(taskId).orElse(null);
        if(returnTask == null){
            throw new Exception("Task not found!");
        }
        return returnTask;
    }

    /** Creates new tasks using given values
     *
     * @param name
     * @param description
     * @return task
     */

    public Task createTask(String name, String description){

        Task task = new Task(name, description);
        taskRepository.save(task);
        return task;
    }

    /** Updates tasks by given id
     *
     * @param id
     * @param newName
     * @param newDescription
     * @return Task
     * @throws Exception
     */

    public Task updateTask(Long id, String newName, String newDescription) throws Exception {
        Task taskToUpdate = taskRepository.findById(id).orElse(null);
        if(taskToUpdate == null){
            throw new Exception("Task not found!");
        }
        taskToUpdate.setDescription(newDescription);
        taskToUpdate.setName(newName);
        return taskToUpdate;
    }
}
