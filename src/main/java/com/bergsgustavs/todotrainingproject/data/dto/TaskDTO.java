package com.bergsgustavs.todotrainingproject.data.dto;

import java.io.Serializable;

public class TaskDTO implements Serializable {

    private Long id;

    private String name;

    private String description;

    private String startingDate;

    private String endingDate;


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


}
