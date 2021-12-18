package com.tsadigov.etaskify.controller;

import com.tsadigov.etaskify.config.MapperConfig;
import com.tsadigov.etaskify.domain.Task;
import com.tsadigov.etaskify.domain.TaskStatus;
import com.tsadigov.etaskify.dto.TaskDTO;
import com.tsadigov.etaskify.dto.TaskStatusDTO;
import com.tsadigov.etaskify.service.TaskStatusService;
import com.tsadigov.etaskify.service.TaskStatusServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/status")
@RequiredArgsConstructor
public class TaskStatusController {

    private final TaskStatusServiceImpl statusServiceImpl;
    private final MapperConfig mapper;

    @GetMapping("/{name}")
    TaskStatus getTaskStatusByName(String name){
        TaskStatus status = statusServiceImpl.getTaskStatusByName(name);
        return status;
    }

    @GetMapping
    List<TaskStatus> getTaskStatuses(){
        List<TaskStatus> taskStatusList = statusServiceImpl.getTaskStatuses();
        return taskStatusList;
    }

    @PostMapping
    void create(@RequestBody TaskStatusDTO taskStatusDTO){
        statusServiceImpl.createTaskStatus(taskStatusDTO);
    }

}
