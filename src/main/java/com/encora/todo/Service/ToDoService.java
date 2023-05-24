package com.encora.todo.Service;

import com.encora.todo.Entity.ToDo;

import java.util.List;
import java.util.Map;

public interface ToDoService {
    Map<String, Object> getAllToDo(String name, String priority, String status, String page,  String sortByPriority, String sortByDate);
    public List<ToDo> getToDo(ToDo toDo);

    public ToDo createToDo(ToDo toDo);
    public ToDo updateToDo(int id, ToDo toDo);
    public void updateDone(int id);
    public void updateUndone(int id);
    public void deleteTodo(int id);
}
