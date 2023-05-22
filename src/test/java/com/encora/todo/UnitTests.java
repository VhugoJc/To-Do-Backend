package com.encora.todo;

import com.encora.todo.Entity.ToDo;
import com.encora.todo.Repository.ToDoRepo;
import com.encora.todo.Service.ToDoServiceIMPL.ToDoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Map;

@SpringBootTest
public class UnitTests {
    @Autowired
    ToDoRepo todoRepo;
    @Autowired
    ToDoServiceImpl impl;

    @Test
    public void createToDoTest () {
        ToDo todo = new ToDo();
        todo.setName("Task 1");
        todo.setPriority(ToDo.Priority.valueOf("low"));
        todo.setCreatedDate(LocalDateTime.parse("2023-05-18T10:08:47.316615"));
        this.impl.createToDo(todo);
        Assertions.assertNotNull(this.todoRepo.findAll());
    }
    @Test
    public void doneToDoTest (){
        ToDo todo = new ToDo();
        todo.setName("Task 2");
        todo.setPriority(ToDo.Priority.valueOf("medium"));
        todo.setCreatedDate(LocalDateTime.parse("2023-05-18T10:08:47.316615"));
        this.impl.createToDo(todo); //create element in the list with id 2
        this.impl.updateDone(1);
        assertThat(this.todoRepo.findAll().get(0).isDone()).isEqualTo(true);
    }
    @Test
    public void updateToDoTest (){
        ToDo todo = new ToDo();
        todo.setName("Task x");
        todo.setPriority(ToDo.Priority.valueOf("medium"));
        todo.setCreatedDate(LocalDateTime.parse("2023-05-18T10:08:47.316615"));
        this.impl.updateToDo(1,todo);
        assertThat(this.todoRepo.findAll().get(0).getName()).isEqualTo("Task x");
    }
    @Test
    public void undoneToDoTest(){
        this.impl.updateUndone(1);
        assertThat(this.todoRepo.findAll().get(0).isDone()).isEqualTo(false);
    }
    @Test
    public void getToDoTest(){
        Map<String,Object> filteredTodos =  this.impl.getAllToDo("Task", "medium","done", "1");
        assertThat(filteredTodos.get("totalPages").toString()).isEqualTo("1");
    }
    @Test
    public void deleteToDoTest(){
        this.impl.deleteTodo(1);
        assertThat(this.todoRepo.size()).isEqualTo(1);
    }

}
