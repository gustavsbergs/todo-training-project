package com.bergsgustavs.todotrainingproject.data.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Objects;

@Entity
@Table(name = "Tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STARTING_DATE")
    private String startingDate;

    @Column(name = "ENDING_DATE")
    private String endingDate;

    @Column(name = "STARTING_TIME")
    private String startingTime;

    @Column(name = "ENDING_TIME")
    private String endingTime;

    public Task(final String name, final String description, final String startingDate, final String endingDate, final String startingTime, final String endingTime) {
        this.name = name;
        this.description = description;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }

    public Task(){
    }

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
        Task task = (Task) o;
        return id.equals(task.id) &&
                name.equals(task.name) &&
                description.equals(task.description) &&
                startingDate.equals(task.startingDate) &&
                endingDate.equals(task.endingDate) &&
                startingTime.equals(task.startingTime) &&
                endingTime.equals(task.endingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, startingDate, endingDate, startingTime, endingTime);
    }

    @Override
    public String toString() {
        return "Task{" +
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
