package com.bergsgustavs.todotrainingproject.services;

import com.bergsgustavs.todotrainingproject.data.domain.Task;
import com.bergsgustavs.todotrainingproject.data.repositories.TaskRepository;
import com.bergsgustavs.todotrainingproject.exceptions.TaskNotFoundException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class TaskServiceTest {

    private final Long id = 1L;

    @Mock
    private TaskRepository taskRepository;

    private TaskService taskService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

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
        final Task retrievedTask = taskService.getTask(id);

        assertNotNull(retrievedTask);
        assertEquals(id, retrievedTask.getId());
    }

    @Test
    public void getTaskNotFound() throws TaskNotFoundException {
        when(taskRepository.findById(id)).thenReturn(Optional.empty());
        expectedException.expect(TaskNotFoundException.class);
        expectedException.expectMessage("Task with id " + id + " not found");
        taskService.getTask(id);
    }

    @Test
    public void createTask() {
        final Task task = new Task();
        task.setId(id);
        when(taskRepository.save(eq(task))).thenReturn(task);
        final Task savedTask = taskService.createTask(task);

        assertNotNull(savedTask);
        verify(taskRepository).save(task);
    }

    @Test
    public void updateTask() throws Exception {
        final String name = "testName";
        final String description = "testDescription";
        final Task task = new Task();
        task.setId(id);
        task.setName(name);
        task.setDescription(description);
        final Optional<Task> taskOptional = Optional.of(task);

        final Task task2 = new Task();
        task2.setId(id);
        task2.setName("updatedName");
        task2.setDescription("updatedDescription");

        when(taskRepository.getOne(id)).thenReturn(task);
        when(taskRepository.findById(id)).thenReturn(taskOptional);
        when(taskRepository.save(eq(task2))).thenReturn(task2);

        final Task updatedTask = taskService.updateTask(id, task2);

        assertEquals("updatedDescription", updatedTask.getDescription());
        assertEquals("updatedName", updatedTask.getName());
        verify(taskRepository).save(eq(task2));
    }

    @Test
    public void updateTaskNotFound() throws Exception {
        Task task = new Task();
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());
        expectedException.expect(TaskNotFoundException.class);
        expectedException.expectMessage("Task with id " + id + " not found!");

        taskService.updateTask(1L, task);
    }

    @Test
    public void deleteTask() throws Exception {
        final Task task = new Task();
        task.setId(id);
        final Optional<Task> taskOptional = Optional.of(task);
        when(taskRepository.findById(id)).thenReturn(taskOptional);

        taskService.deleteTask(id);

        verify(taskRepository).deleteById(id);
    }

    @Test
    public void deleteTaskNotFound() throws Exception {
        when(taskRepository.findById(id)).thenReturn(Optional.empty());
        expectedException.expect(TaskNotFoundException.class);
        expectedException.expectMessage("Task with id " + id + " not found or already deleted!");

        taskService.deleteTask(id);
    }

    @Test
    public void returnAll() {
        final List<Task> tasks = new ArrayList<>();
        Task task = new Task();
        task.setId(id);
        tasks.add(task);

        when(taskRepository.findAll()).thenReturn(tasks);
        final List<Task> returnedTasks = taskService.returnAll();
        assertEquals(task.getId(), returnedTasks.get(0).getId());
    }
}