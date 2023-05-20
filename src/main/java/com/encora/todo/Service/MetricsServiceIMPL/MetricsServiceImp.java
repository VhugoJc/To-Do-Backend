package com.encora.todo.Service.MetricsServiceIMPL;

import com.encora.todo.Entity.ToDo;
import com.encora.todo.Repository.ToDoRepo;
import com.encora.todo.Service.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class MetricsServiceImp implements MetricsService {
    @Autowired
    ToDoRepo toDoRepo;
    @Override
    public Map<String, Object> getMetrics() {
        List<ToDo> toDos = toDoRepo.findAll();

        long lowPriorityMinutes = 0;
        long mediumPriorityMinutes = 0;
        long highPriorityMinutes = 0;

        int doneElements = 0;
        int lowPriorityElements = 0;
        int mediumPriorityElements = 0;
        int highPriorityElements = 0;


        for(int i=0; i<toDos.size();i++){
            if(toDos.get(i).isDone()){
                doneElements++;
                // define start and finish time
                LocalDateTime start = toDos.get(i).getCreatedDate();
                LocalDateTime finish = toDos.get(i).getDoneDate();

                // priority switch case
                switch (toDos.get(i).getPriotity().toString()){
                    case "low":
                        lowPriorityElements++;
                        lowPriorityMinutes = ChronoUnit.MINUTES.between(start,finish);
                        break;
                    case "medium":
                        mediumPriorityElements++;
                        mediumPriorityMinutes = ChronoUnit.MINUTES.between(start,finish);
                        break;
                    case "high":
                        highPriorityElements++;
                        highPriorityMinutes = ChronoUnit.MINUTES.between(start,finish);
                }
            }
        }

        Map<String,Object> response = new HashMap<>();
        response.put("totalMinutes",averageValidator(lowPriorityMinutes+mediumPriorityMinutes+highPriorityMinutes,doneElements));
        response.put("lowPriorityMinutes",averageValidator(lowPriorityMinutes,lowPriorityElements));
        response.put("mediumPriorityMinutes",averageValidator(mediumPriorityMinutes,mediumPriorityElements));
        response.put("highPriorityMinutes",averageValidator(highPriorityMinutes,highPriorityElements));
        return response;
    }

    private long averageValidator(long minutes, int elements){
        if(elements==0){
            return 0;
        }else {
            return minutes/elements;
        }
    }
}

