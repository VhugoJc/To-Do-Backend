package com.encora.todo.Controller;

import com.encora.todo.Entity.ToDo;
import com.encora.todo.Service.ToDoServiceIMPL.ToDoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class Controller {

    @Autowired
    private ToDoServiceImpl impl; //To Do Service Implementation
    @GetMapping // GET /api/todos
    @RequestMapping(value = "todos",method = RequestMethod.GET)
    public ResponseEntity<?> toDoGet(){
        List<ToDo> toDos=this.impl.getAllToDo();
        return ResponseEntity.ok(toDos);
    }
    @PostMapping // POST /api/todos
    @RequestMapping(value = "todos",method = RequestMethod.POST)
    public ResponseEntity<?> toDoPost(@Valid @RequestBody ToDo newToDo){
        List<ToDo> toDos=this.impl.createToDo(newToDo);
        return ResponseEntity.ok(toDos);
    }
    @PutMapping // Put /api/todos/id
    @RequestMapping(value = "todos/{id}",method = RequestMethod.PUT)
    public ResponseEntity<?> toDoUpdate(@Valid @RequestBody ToDo newToDo, @PathVariable int id){
        List<ToDo> allMyToDos = this.impl.updateToDo(id,newToDo);
        return ResponseEntity.ok(allMyToDos);
    }
    @PostMapping // Post /api/todos/id/done
    @RequestMapping(value = "todos/{id}/done",method = RequestMethod.POST)
    public ResponseEntity<?> toDoPostDone(@PathVariable int id){
        this.impl.updateDone(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping // Put /api/todos/id/done
    @RequestMapping(value = "todos/{id}/undone",method = RequestMethod.PUT)
    public ResponseEntity<?> toDoPutUndone(@PathVariable int id){
        this.impl.updateUndone(id);
        return ResponseEntity.ok().build();
    }
}
