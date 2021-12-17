package com.tsadigov.etaskify.service;

import com.tsadigov.etaskify.domain.TaskStatus;
import com.tsadigov.etaskify.repository.TaskStatusRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskStatusServiceImpl implements TaskStatusService{

    private final TaskStatusRepo statusRepo;

    @Override
    public TaskStatus getTaskStatusByName(String name) {
        return statusRepo.findTaskStatusByName(name);
    }

    @Override
    public List<TaskStatus> getTaskStatuses() {
        return null ;
    }

    @Override
    public void createTaskStatus(TaskStatus taskStatus) {

    }

    @Override
    public void deleteTaskStatus(Long id) {

    }
}
