package com.bergsgustavs.todotrainingproject.data.dto;

import java.io.Serializable;
import java.util.Objects;

public class TaskDTO implements Serializable {

    private Long id;

    private String name;

    private String description;

    private String startingDate;

    private String endingDate;

    private String startingTime;

    private String endingTime;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(final String startingDate) {
        this.startingDate = startingDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(final String endingDate) {
        this.endingDate = endingDate;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(final String startingTime) {
        this.startingTime = startingTime;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(final String endingTime) {
        this.endingTime = endingTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDTO taskDTO = (TaskDTO) o;
        return id.equals(taskDTO.id) &&
                name.equals(taskDTO.name) &&
                description.equals(taskDTO.description) &&
                startingDate.equals(taskDTO.startingDate) &&
                endingDate.equals(taskDTO.endingDate) &&
                startingTime.equals(taskDTO.startingTime) &&
                endingTime.equals(taskDTO.endingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, startingDate, endingDate, startingTime, endingTime);
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startingDate='" + startingDate + '\'' +
                ", endingDate='" + endingDate + '\'' +
                ", startingTime='" + startingTime + '\'' +
                ", endingTime='" + endingTime + '\'' +
                '}';
    }
}
