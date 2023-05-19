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
        toDo.setDoneDate(null);
        return toDoRepo.add(toDo);
    }

    @Override
    public List<ToDo> updateToDo(int id, ToDo toDo) {
        List<ToDo>allToDo = toDoRepo.findAll();
        for (int i=0; i<allToDo.size();i++){
            if(allToDo.get(i).getId()==id){
                toDoRepo.update(i,toDo);
            }
        }
        return toDoRepo.findAll();
    }

    @Override
    public void updateDone(int id) {
        List<ToDo>allToDo = toDoRepo.findAll();
        for (int i=0; i<allToDo.size();i++){
            if(allToDo.get(i).getId()==id){
                allToDo.get(i).setDone(true); // mark as done
                allToDo.get(i).setDoneDate(LocalDateTime.now()); // assign current date
                toDoRepo.update(i,allToDo.get(i));
            }
        }
    }
    @Override
    public void updateUndone(int id) {
        List<ToDo>allToDo = toDoRepo.findAll();
        for (int i=0; i<allToDo.size();i++){
            if(allToDo.get(i).getId()==id){
                allToDo.get(i).setDone(false); // mark as done
                allToDo.get(i).setDoneDate(null); // assign current date
                toDoRepo.update(i,allToDo.get(i));
            }
        }
    }
}
