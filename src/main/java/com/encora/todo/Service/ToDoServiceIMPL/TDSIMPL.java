package com.encora.todo.Service.ToDoServiceIMPL;

import com.encora.todo.Entity.ToDo;
import com.encora.todo.Repository.ToDoRepo;
import com.encora.todo.Service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TDSIMPL implements ToDoService {
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

        return toDoRepo.add(toDo);
    }
}
