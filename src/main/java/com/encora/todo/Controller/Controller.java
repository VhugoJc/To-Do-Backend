package com.encora.todo.Controller;

import com.encora.todo.Entity.ToDo;
import com.encora.todo.Service.MetricsServiceIMPL.MetricsServiceImp;
import com.encora.todo.Service.ToDoServiceIMPL.ToDoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class Controller {

    @Autowired
    private ToDoServiceImpl toDoImpl; //To Do Service Implementation
    @Autowired
    private MetricsServiceImp metricsImpl; // Metrics Service Implementation
    @GetMapping // GET /api/todos
    @RequestMapping(value = "todos",method = RequestMethod.GET)
    public ResponseEntity<?> toDoGet(String name, String priority,String status, String page){ //@RequestParam
        Map<String,Object> toDos=this.toDoImpl.getAllToDo(name, priority, status, page);
        return ResponseEntity.ok(toDos);
    }
    @PostMapping // POST /api/todos
    @RequestMapping(value = "todos",method = RequestMethod.POST)
    public ResponseEntity<?> toDoPost(@Valid @RequestBody ToDo newToDo){
        ToDo toDo=this.toDoImpl.createToDo(newToDo);
        return ResponseEntity.ok(toDo);
    }
    @PutMapping // Put /api/todos/id
    @RequestMapping(value = "todos/{id}",method = RequestMethod.PUT)
    public ResponseEntity<?> toDoUpdate(@Valid @RequestBody ToDo newToDo, @PathVariable int id){
        ToDo updatedTodo = this.toDoImpl.updateToDo(id,newToDo);
        return ResponseEntity.ok(updatedTodo);
    }
    @PostMapping // Post /api/todos/id/done
    @RequestMapping(value = "todos/{id}/done",method = RequestMethod.POST)
    public ResponseEntity<?> toDoPostDone(@PathVariable int id){
        this.toDoImpl.updateDone(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping // Put /api/todos/id/done
    @RequestMapping(value = "todos/{id}/undone",method = RequestMethod.PUT)
    public ResponseEntity<?> toDoPutUndone(@PathVariable int id){
        this.toDoImpl.updateUndone(id);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping
    @RequestMapping(value = "todo/{id}", method = RequestMethod.DELETE)
    public  ResponseEntity<?> toDoDelete(@PathVariable int id){
        this.toDoImpl.deleteTodo(id);
        return ResponseEntity.ok().build();
    }

    //metrics
    @GetMapping
    @RequestMapping(value = "metrics", method = RequestMethod.GET)
    public  ResponseEntity<?> metricsGet(){
        Map<String,Object> metrics = this.metricsImpl.getMetrics();
        return ResponseEntity.ok(metrics);
    }

}
