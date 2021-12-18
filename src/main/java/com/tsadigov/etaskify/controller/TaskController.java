package com.tsadigov.etaskify.controller;

import com.tsadigov.etaskify.domain.Task;
import com.tsadigov.etaskify.dto.ResponseDTO;
import com.tsadigov.etaskify.dto.TaskDTO;
import com.tsadigov.etaskify.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tsadigov.etaskify.bootstap.Constants.*;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllTasks(){

        List<Task> taskList = taskService.getTasks();

        ResponseDTO responseDTO = ResponseDTO.builder()
                .code(SUCCESS_CODE)
                .message(SUCCESS)
                .response(taskList)
                .build();

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createTask(@RequestBody TaskDTO taskDTO){

        Task task = taskService.create(taskDTO);

        ResponseDTO responseDTO = ResponseDTO.builder()
                .code(SUCCESS_CODE)
                .message(SUCCESS)
                .response(task)
                .build();

        return ResponseEntity.ok(responseDTO);
    }

}
