package com.encora.todo.Service;

import com.encora.todo.Entity.ToDo;

import java.util.List;

public interface ToDoService {
    List<ToDo> ConsultTodo();

    public List<ToDo> ConsultTodo(ToDo toDo);
    public ToDo CreateToDo(ToDo toDo);
    public ToDo ReadToDo(int id);
    public ToDo UpdateToDo(ToDo toDo);
    public void DeleteTodo(int id);

}
