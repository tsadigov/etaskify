package com.tsadigov.etaskify.service;

import com.tsadigov.etaskify.domain.Task;
import com.tsadigov.etaskify.dto.TaskDTO;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Optional<Task> getTask(Long id);
    List<Task> getTasks();
    Task create(TaskDTO taskDTO);
    Task update(Long id);
    void updateStatus(String status);

}
