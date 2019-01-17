package com.bergsgustavs.todotrainingproject.services;

import com.bergsgustavs.todotrainingproject.data.domain.Task;
import com.bergsgustavs.todotrainingproject.data.repositories.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {


    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /** Returns tasks by id.
     *
     * @param taskId
     * @return task
     */
    public Task getTask(Long taskId) throws Exception {

        final Task task = taskRepository.findById(taskId).orElse(null);

        if(task == null){
            throw new Exception("Task not found!");
        }
        return task;
    }

    /** Creates new tasks using given values
     *
     * @param newTask
     * @return task
     */
    public Task createTask(Task newTask){

        final Task task = newTask;

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

        final Task taskToUpdate = taskRepository.getOne(id);

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

        final Task taskToDelete = taskRepository.findById(id).orElse(null);

        if(taskToDelete == null){
            throw new Exception("Task not found or already deleted!");
        }
        taskRepository.deleteById(id);
    }
}
