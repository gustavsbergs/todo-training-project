package com.bergsgustavs.todotrainingproject.data.mappers;

import com.bergsgustavs.todotrainingproject.data.domain.Task;
import com.bergsgustavs.todotrainingproject.data.dto.TaskDTO;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class DTOMapperTest {

    private DTOMapper dtoMapper;
    final private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private String name;
    private String description;
    private Date startingDate;
    private Date endingDate;
    private String startingDateString;
    private String endingDateString;

    @Before
    public void setUp() throws ParseException {
        dtoMapper = new DTOMapper();
        startingDate = simpleDateFormat.parse("2000-01-01 00:00");
        endingDate = simpleDateFormat.parse("2000-01-01 01:00");
        description = "testDescription";
        name = "testName";
        startingDateString = "2000-01-01 00:00";
        endingDateString = "2000-01-01 01:00";
    }

    @Test
    public void taskToDTO() {
        Task task = new Task(name, description, startingDate, endingDate);
        TaskDTO mappedTask = dtoMapper.TaskToDTO(task);

        assertEquals(task.getName(),  mappedTask.getName());
        assertEquals(task.getDescription(),  mappedTask.getDescription());
        assertEquals(simpleDateFormat.format(task.getStartingDate()),  mappedTask.getStartingDate());
        assertEquals(simpleDateFormat.format(task.getEndingDate()),  mappedTask.getEndingDate());
    }

    @Test
    public void DTOToTask() throws ParseException {
        TaskDTO task = new TaskDTO(name, description, startingDateString, endingDateString);
        Task mappedTask = dtoMapper.DTOToTask(task);

        assertEquals(task.getName(),  mappedTask.getName());
        assertEquals(task.getDescription(),  mappedTask.getDescription());
        assertEquals(task.getStartingDate(), simpleDateFormat.format(mappedTask.getStartingDate()));
        assertEquals(task.getEndingDate(), simpleDateFormat.format(mappedTask.getEndingDate()));
    }
}