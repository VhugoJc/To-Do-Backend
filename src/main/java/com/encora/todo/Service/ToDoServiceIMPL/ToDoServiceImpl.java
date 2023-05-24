package com.encora.todo.Service.ToDoServiceIMPL;

import com.encora.todo.Entity.ToDo;
import com.encora.todo.Repository.ToDoRepo;
import com.encora.todo.Service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ToDoServiceImpl implements ToDoService { //All your business logic should be in the Service Layer
    @Autowired
    ToDoRepo toDoRepo;
    private int priorityHelper (String priority) {
        int priorityValue = 0;
        switch (priority){
            case "low":
                priorityValue=1;
                break;
            case "medium":
                priorityValue=2;
                break;
            case "high":
                priorityValue=3;
                break;
        }
        return priorityValue;
    }
    @Override
    public Map<String, Object> getAllToDo(String name, String priority, String status, String page, String sortByPriority, String sortByDate ) {
        List<ToDo>allToDo = toDoRepo.findAll();
        List<ToDo> filteredToDoList = new ArrayList<ToDo>(allToDo) ;
        int currentPage=0;
        int totalPages=0;
        int pageSize=10;

        //priority filter
        if(priority!=null){
            for (int i=0; i<allToDo.size();i++){
                if(!priority.equals(allToDo.get(i).getPriority().toString())) {
                    filteredToDoList.remove(allToDo.get(i)); // add all task with the priority
                }
            }
        }

        //status filter
        if(status!=null){
            for(int j=0; j<=filteredToDoList.size();j++){
                System.out.println(j);
                if(status.equals("done")){
                    if(!filteredToDoList.get(j).isDone()){// remove all undone elements
                        filteredToDoList.remove(j);
                    }
                }

                if(status.equals("undone")){
                    if(filteredToDoList.get(j).isDone()){ // remove all done elements
                        filteredToDoList.remove(j);
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
        // get the total amount of pages
        totalPages= (int) Math.ceil((double) filteredToDoList.size() /pageSize);
        // sort by priority
        if(sortByPriority!=null){
            List<String> kindOfPriority = Arrays.asList("low","medium","high");

            if(sortByPriority.equals("ascend")){
                filteredToDoList.sort((todo1,todo2)->priorityHelper(todo1.getPriority().toString())-priorityHelper(todo2.getPriority().toString()));
            }
            if(sortByPriority.equals("descend")){
                filteredToDoList.sort((todo1,todo2)->priorityHelper(todo1.getPriority().toString())-priorityHelper(todo2.getPriority().toString()));
                Collections.reverse(filteredToDoList);
            }
        }
        // sort by date
        if(sortByDate!=null){
            if(sortByDate.equals("ascend")){
                Collections.sort(filteredToDoList, new Comparator<ToDo>() {
                    public int compare(ToDo o1, ToDo o2) {
                        if (o1.getDueDate() == null) {
                            return (o2.getDueDate() == null) ? 0 : -1;
                        }
                        if (o2.getDueDate() == null) {
                            return 1;
                        }
                        return o2.getDueDate().compareTo(o1.getDueDate());
                    }
                });
            }
            if(sortByDate.equals("descend")){
                Collections.sort(filteredToDoList, new Comparator<ToDo>() {
                    public int compare(ToDo o1, ToDo o2) {
                        if (o1.getDueDate() == null) {
                            return (o2.getDueDate() == null) ? 0 : -1;
                        }
                        if (o2.getDueDate() == null) {
                            return 1;
                        }
                        return o2.getDueDate().compareTo(o1.getDueDate());
                    }
                });
                Collections.reverse(filteredToDoList);
            }
        }

        // pagination
        if(page != null && page.matches("[0-9.]+")){ //if it is numeric
            currentPage= Integer.parseInt(page) - 1;
            if(currentPage>=0 && currentPage*pageSize<filteredToDoList.size() ){ // if it is in the range
                filteredToDoList=filteredToDoList.subList(currentPage*pageSize, Math.min(currentPage*pageSize+pageSize, filteredToDoList.size()));
            }else{
                filteredToDoList=new ArrayList<ToDo>();
            }
        }

        Map<String,Object> response = new HashMap<>();
        response.put("toDos",filteredToDoList);
        response.put("currentPage",currentPage+1);
        response.put("totalPages",totalPages);

        return response ;
    }


    @Override
    public List<ToDo> getToDo(ToDo toDo) {
        return null;
    }

    @Override
    public ToDo createToDo(ToDo toDo) {

        int index = toDoRepo.size();
        toDo.setId(idGenerator()); // autoincrement id
        toDo.setCreatedDate(LocalDateTime.now()); // add created date
        toDo.setDoneDate(null);
        List <ToDo> toDos = toDoRepo.add(toDo);
        return toDos.get(index);
    }

    private int idGenerator () {
        int index = toDoRepo.size();
        List <ToDo> toDos = toDoRepo.findAll();
        if(index==0){
            return 0;
        }
        return toDos.get(index-1).getId()+1;
    }
    @Override
    public ToDo updateToDo(int id, ToDo toDo) {
        List<ToDo>allToDo = toDoRepo.findAll();
        for (int i=0; i<allToDo.size();i++){
            if(allToDo.get(i).getId()==id){
                toDo.setId(allToDo.get(i).getId()); // not change id
                toDo.setCreatedDate(allToDo.get(i).getCreatedDate()); //no change created Date
                toDo.setDone(allToDo.get(i).isDone()); // no change done flag
                toDo.setDoneDate(allToDo.get(i).getDoneDate()); // no change done date
                toDoRepo.update(i,toDo); // update name, due date or/and priority
            }
        }
        return toDo;
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

    @Override
    public void deleteTodo(int id) {
        List<ToDo>allToDo = toDoRepo.findAll();
        for (int i=0; i<allToDo.size();i++){
            if(allToDo.get(i).getId()==id){
                toDoRepo.remove(i);
            }
        }
    }
}
