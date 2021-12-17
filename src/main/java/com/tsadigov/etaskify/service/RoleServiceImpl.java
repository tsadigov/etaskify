package com.tsadigov.etaskify.service;

import com.tsadigov.etaskify.domain.Role;
import com.tsadigov.etaskify.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

    private final RoleRepo roleRepo;

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new ROLE {} to the DB", role.getRoleName());
        return roleRepo.save(role);
    }

}
