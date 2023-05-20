package com.encora.todo.Repository;

import com.encora.todo.Entity.ToDo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class ToDoRepo implements ToDoRep {
    private static List<ToDo> toDos = new ArrayList<ToDo>();

    public List<ToDo> findAll(){
        return toDos;
    }

    public List<ToDo> add(ToDo newToDo){
        toDos.add(newToDo);
        return toDos;
    }
    public int size(){
        return toDos.size();
    }

    public List<ToDo> update(int index, ToDo myTodo){
        toDos.set(index,myTodo);
        return toDos;
    }

    @Override
    public void remove(int index) {
        toDos.remove(index);
    }
}
