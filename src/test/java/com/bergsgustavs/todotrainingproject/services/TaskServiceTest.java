package com.bergsgustavs.todotrainingproject.services;

import com.bergsgustavs.todotrainingproject.data.domain.Task;
import com.bergsgustavs.todotrainingproject.data.repositories.TaskRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.mockito.MockitoAnnotations.initMocks;

public class TaskServiceTest {

    private final Long id = 1L;

    @Mock
    private TaskRepository taskRepository;

    private TaskService taskService;

    @Before
    public void setUp() {
        initMocks(this);

        taskService = new TaskService(taskRepository);

    }

    @Test
    public void getTask() throws Exception {
        final Task task = new Task();
        task.setId(id);
        final Optional<Task> taskOptional = Optional.of(task);

        when(taskRepository.findById(id)).thenReturn(taskOptional);
        Task retrievedTask = taskService.getTask(id);

        assertEquals(id, retrievedTask.getId());
    }

    @Test(expected = Exception.class)
    public void getTaskNotFound() throws Exception {
        taskService.getTask(id);
    }

    @Test
    public void createTask() {

        final Task task = new Task();
        task.setId(id);

        taskService.createTask(task);

        verify(taskRepository).save(task);
    }

    @Test
    public void updateTask() throws Exception {
        final String name = "123name";
        final String description = "123description";
        final Task task = new Task();
        task.setId(id);
        task.setName(name);
        task.setDescription(description);
        final Optional<Task> taskOptional = Optional.of(task);

        final Task task2 = new Task();
        task2.setName("updatedName");
        task2.setDescription("updatedDescription");

        when(taskRepository.getOne(id)).thenReturn(task);
        when(taskRepository.findById(id)).thenReturn(taskOptional);
        Task updatedTask = taskService.updateTask(id, task2);

        assertEquals("updatedDescription", updatedTask.getDescription());
        assertEquals("updatedName", updatedTask.getName());
    }

    @Test(expected = Exception.class)
    public void updateTaskNotFound() throws Exception {
        Task task = new Task();
        taskService.updateTask(1L, task);
    }

    @Test
    public void deleteTask() throws Exception {

        final Task task = new Task();
        task.setId(id);
        final Optional<Task> taskOptional = Optional.of(task);
        taskRepository.save(task);

        when(taskRepository.findById(id)).thenReturn(taskOptional);
        taskService.deleteTask(id);

        verify(taskRepository, times(1)).deleteById(id);
    }

    @Test(expected = Exception.class)
    public void deleteTaskNotFound() throws Exception {
        taskService.deleteTask(id);
    }

    @Test
    public void returnAll() {

        final List<Task> tasks = new ArrayList<>();
        Task task = new Task();
        task.setId(id);
        tasks.add(task);

        when(taskRepository.findAll()).thenReturn(tasks);

        assertEquals(task.getId(), tasks.get(0).getId());
    }
}