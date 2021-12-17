package com.tsadigov.etaskify.repository;

import com.tsadigov.etaskify.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}
