package com.bergsgustavs.todotrainingproject.restcontrollers;

import com.bergsgustavs.todotrainingproject.data.domain.Task;
import com.bergsgustavs.todotrainingproject.data.dto.TaskDTO;
import com.bergsgustavs.todotrainingproject.data.mappers.DTOMapper;
import com.bergsgustavs.todotrainingproject.services.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TaskControllerTest {

    private DTOMapper dtoMapper = new DTOMapper();
    private MockMvc mockMvc;
    final private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private Date sDate1;
    private Date eDate1;
    private Task task1;
    private TaskDTO task1DTO;
    private ObjectMapper mapper;

    @Mock
    private TaskService taskService;

    @Before
    public void setUp() throws ParseException {
        initMocks(this);
        TaskController taskController = new TaskController(taskService, dtoMapper);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
        sDate1 = simpleDateFormat.parse("2000-01-01 00:00");
        eDate1  = simpleDateFormat.parse("2000-01-01 01:00");
        task1 = new Task("Task1", "Description1", sDate1, eDate1);
        task1DTO = dtoMapper.TaskToDTO(task1);
        mapper = new ObjectMapper();
    }

    @Test
    public void getTask() throws Exception {
        when(taskService.getTask(1L)).thenReturn(task1);

        mockMvc.perform(get("/tasks/" + 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json(mapper.writeValueAsString(task1DTO)));
    }

    @Test
    public void createTask() throws Exception {
        when(taskService.createTask(task1)).thenReturn(task1);

        final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/tasks/new")
                .contentType("application/json")
                .content(mapper.writeValueAsString(task1DTO));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json(mapper.writeValueAsString(task1DTO)));
    }


    @Test
    public void updateTask() throws Exception {
        final Task updatedTask = new Task("nameUpdated", "descriptionUpdated", sDate1, eDate1);
        updatedTask.setId(1L);
        final TaskDTO updatedDTO = dtoMapper.TaskToDTO(updatedTask);

        when(taskService.updateTask(1L, updatedTask)).thenReturn(updatedTask);

        final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/tasks/update/"+1L)
                .contentType("application/json")
                .content(mapper.writeValueAsString(updatedDTO));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json(mapper.writeValueAsString(updatedDTO)));
    }

    @Test
    public void deleteTask() throws Exception {
        final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/tasks/delete/"+1L);

        mockMvc.perform(request)
                .andExpect(status().isOk());

        verify(taskService).deleteTask(1L);
    }

    @Test
    public void getAllTasks() throws Exception {
        final List<Task> tasks = new ArrayList<>();
        task1.setId(1L);
        tasks.add(task1);

        when(taskService.returnAll()).thenReturn(tasks);

        final List<TaskDTO> mappedTasks = tasks.stream()
                .map(dtoMapper::TaskToDTO)
                .collect(Collectors.toList());

        final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/tasks");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json(mapper.writeValueAsString(mappedTasks)));
    }
}