package com.encora.todo.Repository;

import com.encora.todo.Entity.ToDo;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ToDoRepo {
    private static List<ToDo> toDos = new ArrayList<ToDo>();

    public List<ToDo> findAll(){
        return toDos;
    }

    public List<ToDo> add(ToDo newToDo){
        newToDo.setId(toDos.size()+1); // autoincrement id
        newToDo.setCreatedDate(LocalDateTime.now());
        toDos.add(newToDo);
        return toDos;
    }
}
