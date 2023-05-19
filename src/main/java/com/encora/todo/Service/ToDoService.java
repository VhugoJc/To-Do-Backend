package com.encora.todo.Service;

import com.encora.todo.Entity.ToDo;

import java.util.List;

public interface ToDoService {
    List<ToDo> getAllToDo();
    public List<ToDo> getToDo(ToDo toDo);

    public List<ToDo> createToDo(ToDo toDo);
    public List<ToDo> updateToDo(int id, ToDo toDo);
    public void updateDone(int id);
}
