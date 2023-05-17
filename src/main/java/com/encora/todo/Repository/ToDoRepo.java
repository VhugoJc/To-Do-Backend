package com.encora.todo.Repository;

import com.encora.todo.Entity.ToDo;
import org.springframework.data.repository.CrudRepository;


public interface ToDoRepo extends CrudRepository<ToDo, Integer> {
}
