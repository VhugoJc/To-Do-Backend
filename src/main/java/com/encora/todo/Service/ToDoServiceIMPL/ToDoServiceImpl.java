package com.encora.todo.Service.ToDoServiceIMPL;

import com.encora.todo.Entity.ToDo;
import com.encora.todo.Repository.ToDoRepo;
import com.encora.todo.Service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ToDoServiceImpl implements ToDoService { //All your business logic should be in the Service Layer
    @Autowired
    ToDoRepo toDoRepo;

    @Override
    public List<ToDo> getAllToDo() {
        return toDoRepo.findAll();
    }

    @Override
    public List<ToDo> getToDo(ToDo toDo) {
        return null;
    }

    @Override
    public List<ToDo> createToDo(ToDo toDo) {
        toDo.setId(toDoRepo.size()+1); // autoincrement id
        toDo.setCreatedDate(LocalDateTime.now()); // add created date
        return toDoRepo.add(toDo);
    }
    private ToDo validateData(ToDo toDo){
        return toDo;
    }

}