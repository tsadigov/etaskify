package com.tsadigov.etaskify.repository;

import com.tsadigov.etaskify.domain.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusRepo extends JpaRepository<TaskStatus, Long> {
    TaskStatus findTaskStatusByName(String name);
}
