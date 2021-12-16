package com.tsadigov.etaskify.repository;

import com.tsadigov.etaskify.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {
    Role findRoleByRoleName(String roleName);
}
