package com.encora.todo.Service.ToDoServiceIMPL;

import com.encora.todo.Entity.ToDo;
import com.encora.todo.Repository.ToDoRepo;
import com.encora.todo.Service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoServiceImpl implements ToDoService { //All your business logic should be in the Service Layer
    @Autowired
    ToDoRepo toDoRepo;

    @Override
    public List<ToDo> getAllToDo(String name,String priority,String status, String page) {
        List<ToDo> filteredToDoList = new ArrayList<ToDo>() ;
        List<ToDo>allToDo = toDoRepo.findAll();

        //priority filter
        if(priority!=null){
            for (int i=0; i<allToDo.size();i++){
                if(priority.equals(allToDo.get(i).getPriotity().toString())) {
                    filteredToDoList.add(allToDo.get(i));
                }
            }
        }else{
            filteredToDoList=allToDo;
        }
        //status filter
        if(status!=null){
            for(int j=0; j<filteredToDoList.size();j++){
                if(status.equals("done")){
                    if(!filteredToDoList.get(j).isDone()){// remove all undone elements
                        filteredToDoList.remove(filteredToDoList.get(j));
                    }
                }
                if(status.equals("undone")){
                    if(filteredToDoList.get(j).isDone()){ // remove all done elements
                        filteredToDoList.remove(filteredToDoList.get(j));
                    }
                }
            }
        }
        //name filter
        if(name!=null){
            for(int k=0; k<filteredToDoList.size();k++) {
                if(!filteredToDoList.get(k).getName().contains(name)){
                    filteredToDoList.remove(filteredToDoList.get(k));
                }
            }
        }
        // pagination
        if(page != null && page.matches("[0-9.]+")){ //if is numeric
            int number = Integer.parseInt(page) - 1;
            if(number>=0 && number*10<filteredToDoList.size() ){ // if in the range
                filteredToDoList=filteredToDoList.subList(number*10, Math.min(number*10+10, filteredToDoList.size()));
            }else{
                filteredToDoList=new ArrayList<ToDo>();
            }
        }


        return filteredToDoList ;
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
