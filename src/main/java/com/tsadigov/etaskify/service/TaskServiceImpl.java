package com.tsadigov.etaskify.service;

import com.tsadigov.etaskify.config.Mapper;
import com.tsadigov.etaskify.domain.AppUser;
import com.tsadigov.etaskify.domain.Task;
import com.tsadigov.etaskify.domain.TaskStatus;
import com.tsadigov.etaskify.dto.TaskCreateDTO;
import com.tsadigov.etaskify.dto.TaskDTO;
import com.tsadigov.etaskify.exception.ResourceNotFoundException;
import com.tsadigov.etaskify.repository.AppUserRepo;
import com.tsadigov.etaskify.repository.TaskRepo;
import com.tsadigov.etaskify.repository.TaskStatusRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;
    private final AppUserRepo userRepo;
    private final TaskStatusRepo taskStatusRepo;
    private final UserServiceImpl userServiceImpl;

    @Override
    public Optional<Task> getTask(Long id) {
        return taskRepo.findById(id);
    }

    @Override
    public List<TaskDTO> getTasks() {
        List<Task> taskList = taskRepo.findAll();
        List<TaskDTO> taskDTOList = Mapper.mapAll(taskList, TaskDTO.class);
        return taskDTOList;
    }

    @Override
    public TaskDTO create(TaskCreateDTO taskCreateDTO) {

        Task task = new Task();
        TaskStatus taskStatus;

        try {
            taskStatus = taskStatusRepo.findTaskStatusByName(taskCreateDTO.getStatus());
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Status " + taskCreateDTO.getStatus() + " not found");
        }

        task.setStatus(taskStatus);
        task.setTitle(taskCreateDTO.getTitle());
        task.setDeadline(taskCreateDTO.getDeadline());
        task.setDescription(taskCreateDTO.getDescription());

        List<String> usernames = taskCreateDTO.getUsernames();
        addUsersToTask(task, usernames);
        log.info("Assigned task to user(s): {}", usernames);

        taskRepo.save(task);

        TaskDTO taskDTO = Mapper.map(task, TaskDTO.class);

        return taskDTO;
    }

    @Override
    public Task update(Long id) {
        return null;
    }

    @Override
    public void updateStatus(String status) {

    }

    public void addUsersToTask(Task task, List<String> usernames) {

        List<AppUser> users = new ArrayList<>();

        usernames.stream().forEach(username -> {
            AppUser user = userRepo.findByUsername(username);
            users.add(user);
//            System.out.println("User email: " + userServiceImpl.getUserEmailByUsername(username));
        });
        task.setUsers(users);
    }

    private List<String> getUsernamesByUserList(Collection<AppUser> users){
        List<String> usernames = new ArrayList<>();

        users.stream().map(appUser -> usernames.add(appUser.getUsername())).collect(Collectors.toList());

        return usernames;
    }

}
