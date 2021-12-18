package com.tsadigov.etaskify.service;

import com.tsadigov.etaskify.domain.AppUser;
import com.tsadigov.etaskify.domain.Task;
import com.tsadigov.etaskify.dto.TaskDTO;
import com.tsadigov.etaskify.repository.AppUserRepo;
import com.tsadigov.etaskify.repository.TaskRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;
    private final AppUserRepo userRepo;


    @Override
    public Optional<Task> getTask(Long id) {
        return taskRepo.findById(id);
    }

    @Override
    public List<Task> getTasks() {
        return taskRepo.findAll();
    }

    @Override
    public Task create(TaskDTO taskDTO) {

        Task task = new Task(null, taskDTO.getTitle(), taskDTO.getDescription(), taskDTO.getDeadline(), taskDTO.getStatus(), new ArrayList<>());
        System.out.println("----------------TASK" + task);

        List<String> usernames = taskDTO.getUsernames();
        usernames.stream().forEach(username -> {
            AppUser user = userRepo.findByUsername(username);
            task.getUsers().add(user);
        });
        log.info("Assigned task to user(s): {}",usernames);

//        TaskStatus

        taskRepo.save(task);

        return task;
    }

    @Override
    public Task update(Long id) {
        return null;
    }

    @Override
    public void updateStatus(String status) {

    }
}
