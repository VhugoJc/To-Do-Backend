package com.encora.todo.Controller;

import com.encora.todo.Entity.ToDo;
import com.encora.todo.Service.ToDoServiceIMPL.TDSIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class Controller {

    @Autowired
    private TDSIMPL impl; //To Do Service Implementation
    @PostMapping // POST api/todos
    @RequestMapping(value = "todos",method = RequestMethod.POST)
    public ResponseEntity<?> toDoPost(@RequestBody ToDo newToDo){
        List<ToDo> toDos=this.impl.createToDo(newToDo);
        return ResponseEntity.ok(toDos);
    }
    @GetMapping // GET api/to-do
    @RequestMapping(value = "to-do",method = RequestMethod.GET)
    public ResponseEntity<?> toDoGet(){
        List<ToDo> toDos=this.impl.getAllToDo();
        return ResponseEntity.ok(toDos);
    }
}
