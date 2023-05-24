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
import java.util.concurrent.TimeUnit;

@Service
public class MetricsServiceImp implements MetricsService {
    @Autowired
    ToDoRepo toDoRepo;
    @Override
    public Map<String, Object> getMetrics() {
        List<ToDo> toDos = toDoRepo.findAll();

        float lowPriorityMinutes = 0;
        float mediumPriorityMinutes = 0;
        float highPriorityMinutes = 0;

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
                switch (toDos.get(i).getPriority().toString()){
                    case "low":
                        doneElements++;
                        lowPriorityElements++;
                        lowPriorityMinutes += (float) ChronoUnit.SECONDS.between(start, finish);
                        break;
                    case "medium":
                        doneElements++;
                        mediumPriorityElements++;
                        mediumPriorityMinutes += (float) ChronoUnit.SECONDS.between(start, finish);
                        break;
                    case "high":
                        doneElements++;
                        highPriorityElements++;
                        highPriorityMinutes += (float) ChronoUnit.SECONDS.between(start, finish);
                }
            }
        }

        float totalMinutes = lowPriorityMinutes + mediumPriorityMinutes + highPriorityMinutes;
        int totalElements = lowPriorityElements + mediumPriorityElements + highPriorityElements;

        Map<String,Object> response = new HashMap<>();
        response.put("lowPriorityMinutes",averageValidator(lowPriorityMinutes,lowPriorityElements));
        response.put("mediumPriorityMinutes",averageValidator(mediumPriorityMinutes,mediumPriorityElements));
        response.put("highPriorityMinutes",averageValidator(highPriorityMinutes,highPriorityElements));
        response.put("totalMinutes",averageValidator(totalMinutes,totalElements));

        return response;
    }

    private String averageValidator(float seconds, int elements){
        if(elements==0){
            return "0";
        }else {
            seconds = seconds/elements;
            int numberOfMinutes = (int) (((seconds % 86400) % 3600) / 60);
            int numberOfSeconds = (int) (((seconds % 86400) % 3600) % 60);
            if(numberOfSeconds<10){
                return numberOfMinutes+":0"+numberOfSeconds;
            }
            return numberOfMinutes+":"+numberOfSeconds;
        }
    }
}

