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
    private ToDoRepo toDoRepo;
    @Override
    public List<ToDo> ConsultTodo() {
        return (List<ToDo>)this.toDoRepo.findAll();
    }

    @Override
    public List<ToDo> ConsultTodo(ToDo toDo) {
        return (List<ToDo>)this.toDoRepo.findAll();
    }

    @Override
    public ToDo CreateToDo(ToDo toDo) {
        toDo.setText(toDo.getText());
        return this.toDoRepo.save(toDo);
    }

    @Override
    public ToDo ReadToDo(int id) {
        return this.toDoRepo.findById(id).get();
    }

    @Override
    public ToDo UpdateToDo(ToDo toDo) {
        return this.toDoRepo.save(toDo);
    }

    @Override
    public void DeleteTodo(int id) {
        this.toDoRepo.deleteById(id);
    }
}
