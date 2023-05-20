package com.encora.todo.Repository;

import com.encora.todo.Entity.ToDo;

import java.util.List;

public interface ToDoRep {
    public List<ToDo> findAll();

    public List<ToDo> add(ToDo newToDo);
    public int size();

    public List<ToDo> update(int index, ToDo myTodo);

    public void remove(int index);
}
