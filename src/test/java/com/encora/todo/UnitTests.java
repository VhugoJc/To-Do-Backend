package com.encora.todo;

import com.encora.todo.Entity.ToDo;
import com.encora.todo.Repository.ToDoRepo;
import com.encora.todo.Service.ToDoServiceIMPL.ToDoServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
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
        ToDo response = this.impl.createToDo(todo); //create a Task with id 0

        assertThat(response.getId()).isEqualTo(0);
    }
        @Test
    public void getToDoTest(){
        Map<String,Object> filteredTodos =  this.impl.getAllToDo("Task", "low","undone", "1", null, null);
        assertThat(filteredTodos.get("totalPages").toString()).isEqualTo("1");
    }
    @Test
    public void updateToDoTest (){
        ToDo todo = new ToDo();
        todo.setName("Task x");
        todo.setPriority(ToDo.Priority.valueOf("medium"));
        todo.setCreatedDate(LocalDateTime.parse("2023-05-18T10:08:47.316615"));
        ToDo response = this.impl.updateToDo(0,todo);

        assertThat(response.getName()).isEqualTo("Task x");
    }
        @Test
    public void deleteToDoTest(){
        this.impl.deleteTodo(0);
        assertThat(this.todoRepo.size()).isEqualTo(0);
    }

}
