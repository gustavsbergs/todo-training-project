package com.bergsgustavs.todotrainingproject.services;

import com.bergsgustavs.todotrainingproject.data.domain.Task;
import com.bergsgustavs.todotrainingproject.data.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Task createTask(String name, String description, String startDate, String endDate, String startTime, String endTime){

        Task task = new Task(name, description, startDate, endDate, startTime, endTime);
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
        Task taskToUpdate = taskRepository.getOne(id);
        if(taskToUpdate == null){
            throw new Exception("Task not found!");
        }
        taskToUpdate.setDescription(newDescription);
        taskToUpdate.setName(newName);

        taskRepository.save(taskToUpdate);

        return taskRepository.findById(id).orElse(null);
    }

    /**
     * Delete task by id.
     * @param id
     * @throws Exception
     */
    public void deleteTask(Long id) throws Exception {
        Task taskToDelete = taskRepository.findById(id).orElse(null);
        if(taskToDelete == null){
            throw new Exception("Task not found or already deleted!");
        }
        taskRepository.deleteById(id);
    }
}
