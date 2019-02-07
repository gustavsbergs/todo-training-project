package com.bergsgustavs.todotrainingproject.data.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Tasks")
public class Task implements Comparable<Task>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    @Lob
    private String description;

    @Column(name = "STARTING_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startingDate;

    @Column(name = "ENDING_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endingDate;

    public Task(final String name, final String description, final Date startingDate, final Date endingDate) {
        this.name = name;
        this.description = description;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public Task() {
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

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(final Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(final Date endingDate) {
        this.endingDate = endingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(name, task.name) &&
                Objects.equals(description, task.description) &&
                Objects.equals(startingDate, task.startingDate) &&
                Objects.equals(endingDate, task.endingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, startingDate, endingDate);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Id: " + id);
        str.append(" Name " + name);
        str.append(" Description: " + description);

        return str.toString();
    }

    @Override
    public int compareTo(Task task){
        if(task.getEndingDate() == null || getEndingDate() == null) {
            return 0;
        }
        return getEndingDate().compareTo(task.getEndingDate());
    }
}
