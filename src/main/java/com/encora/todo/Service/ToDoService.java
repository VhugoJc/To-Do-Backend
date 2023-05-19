package com.encora.todo.Service;

import com.encora.todo.Entity.ToDo;

import java.util.List;

public interface ToDoService {
    List<ToDo> getAllToDo(String name,String priority,String status, String page);
    public List<ToDo> getToDo(ToDo toDo);

    public List<ToDo> createToDo(ToDo toDo);
    public List<ToDo> updateToDo(int id, ToDo toDo);
    public void updateDone(int id);
    public void updateUndone(int id);
}
