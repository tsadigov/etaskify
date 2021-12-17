package com.tsadigov.etaskify.service;

import com.tsadigov.etaskify.domain.TaskStatus;

import java.util.List;

public interface TaskStatusService {

    TaskStatus getTaskStatusByName(String name);
    List<TaskStatus> getTaskStatuses();
    void createTaskStatus(TaskStatus taskStatus);
    void deleteTaskStatus(Long id);

}
