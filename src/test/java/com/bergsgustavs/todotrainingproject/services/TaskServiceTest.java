package com.bergsgustavs.todotrainingproject.services;

import com.bergsgustavs.todotrainingproject.data.domain.Task;
import com.bergsgustavs.todotrainingproject.data.repositories.TaskRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class TaskServiceTest {

    private Long id = 1L;
    private String name = "123name";
    private String description = "123description";

    @Mock
    TaskRepository taskRepository;

    TaskService taskService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        taskService = new TaskService(taskRepository);

    }

    @Test
    public void getTask() throws Exception {

        Task task = new Task();
        task.setId(id);
        Optional<Task> taskOptional = Optional.of(task);

        when(taskRepository.findById(anyLong())).thenReturn(taskOptional);
        Task retrievedTask = taskService.getTask(id);

        assertNotNull(retrievedTask);
        verify(taskRepository, times(1)).findById(anyLong());
        assertEquals(id, retrievedTask.getId());
    }

    @Test
    public void createTask() throws Exception{

        Task task = new Task();
        task.setId(id);

        taskService.createTask(task);

        verify(taskRepository, times(1)).save(any());
    }

    @Test
    public void updateTask() throws Exception {

        Task task = new Task();
        task.setId(id);
        task.setName(name);
        task.setDescription(description);
        Optional<Task> taskOptional = Optional.of(task);

        Task task2 = new Task();
        task2.setName("updated");
        task2.setDescription("updated");

        when(taskRepository.getOne(anyLong())).thenReturn(task);
        when(taskRepository.findById(anyLong())).thenReturn(taskOptional);
        Task updatedTask = taskService.updateTask(id, task2);

        verify(taskRepository, times(1)).getOne(anyLong());
        verify(taskRepository, times(1)).save(any());
        verify(taskRepository, times(1)).findById(anyLong());
        assertEquals("updated", updatedTask.getDescription());
        assertEquals("updated", updatedTask.getName());
    }

    @Test
    public void deleteTask() throws Exception {

        Task task = new Task();
        task.setId(id);
        Optional<Task> taskOptional = Optional.of(task);
        taskRepository.save(task);

        when(taskRepository.findById(anyLong())).thenReturn(taskOptional);
        taskService.deleteTask(id);

        verify(taskRepository, times(1)).findById(anyLong());
        verify(taskRepository, times(1)).deleteById(anyLong());
    }
}