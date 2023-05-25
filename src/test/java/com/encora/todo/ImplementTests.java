package com.encora.todo;

import com.encora.todo.Entity.ToDo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class ImplementTests extends AbstractTest  {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void createProduct() throws Exception {
        ToDo newTodo = new ToDo();
        newTodo.setName("Task 11");
        newTodo.setPriority(ToDo.Priority.valueOf("low"));

        String inputJson = super.mapToJson(newTodo);
        MvcResult mvcResult  = this.mockMvc.perform(MockMvcRequestBuilders.post("/api/todos").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        ToDo createdTodo = super.mapFromJson(mvcResult.getResponse().getContentAsString(),ToDo.class);

        assertThat(status).isEqualTo(200);
        assertThat(createdTodo.getId()).isEqualTo(0);
    }
}
