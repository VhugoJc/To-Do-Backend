package com.encora.todo.Controller;

import com.encora.todo.Entity.ToDo;
import com.encora.todo.Service.ToDoServiceIMPL.TDSIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class Controller {
    @Autowired
    private TDSIMPL impl;
    @GetMapping
    @RequestMapping(value = "toDoConsult",method = RequestMethod.GET)
    public ResponseEntity<?> toDoConsult(){
        List<ToDo> toDoList = this.impl.ConsultTodo();
        return ResponseEntity.ok(toDoList);
    }
    @PostMapping
    @RequestMapping(value = "toDoCreate",method = RequestMethod.POST)
    public ResponseEntity<?> toDoCreate(@RequestBody ToDo toDo){
        ToDo newToDo = this.impl.CreateToDo(toDo);
        return ResponseEntity.ok(newToDo);
    }
    @PutMapping
    @RequestMapping(value = "toDoEdit",method = RequestMethod.PUT)
    public ResponseEntity<?> toDoEdit(@RequestBody ToDo toDo){
        ToDo updatedToDo = this.impl.UpdateToDo(toDo);
        return ResponseEntity.ok(updatedToDo);
    }
    @GetMapping
    @RequestMapping(value = "toDoRead/{id}",method = RequestMethod.PUT)
    public ResponseEntity<?> toDoRead(@PathVariable int id){
        ToDo myToDo = this.impl.ReadToDo(id);
        return ResponseEntity.ok(myToDo);
    }
    @DeleteMapping
    @RequestMapping(value = "toDoDelete/{id}",method = RequestMethod.PUT)
    public ResponseEntity<?> toDoDelete(@PathVariable int id){
        return ResponseEntity.ok().build();
    }
}
