package com.tsadigov.etaskify.repository;

import com.tsadigov.etaskify.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task,Long> {
}
