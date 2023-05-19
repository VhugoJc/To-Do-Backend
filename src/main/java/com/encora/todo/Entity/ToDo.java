package com.encora.todo.Entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class ToDo {
    private int id;
    @NotNull
    @NotEmpty
    private String name;
    @DateTimeFormat
    private LocalDateTime dueDate;
    @DateTimeFormat
    private LocalDateTime doneDate;
    @DateTimeFormat
    private LocalDateTime createdDate;
    @NotNull
    private Priotity priotity;
    private boolean done;

    public enum Priotity {
       low, medium, high;
    }

    public Priotity getPriotity() {
        return priotity;
    }

    public void setPriotity(Priotity priotity) {
        this.priotity = priotity;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(LocalDateTime doneDate) {
        this.doneDate = doneDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }


    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
