package com.tsadigov.etaskify.service;

import com.tsadigov.etaskify.domain.TaskStatus;
import com.tsadigov.etaskify.dto.TaskStatusDTO;

import java.util.List;

public interface TaskStatusService {

    TaskStatus getTaskStatusByName(String name);
    List<TaskStatus> getTaskStatuses();
    void createTaskStatus(TaskStatusDTO taskStatusDTO);
    void deleteTaskStatus(Long id);

}
