package com.tsadigov.etaskify.bootstap;

import com.tsadigov.etaskify.domain.AppUser;
import com.tsadigov.etaskify.domain.Role;
import com.tsadigov.etaskify.domain.TaskStatus;
import com.tsadigov.etaskify.repository.OrganizationRepo;
import com.tsadigov.etaskify.repository.TaskStatusRepo;
import com.tsadigov.etaskify.service.RoleService;
import com.tsadigov.etaskify.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static com.tsadigov.etaskify.bootstap.Constants.ROLE_ADMIN;
import static com.tsadigov.etaskify.bootstap.Constants.ROLE_EMPLOYEE;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {


    private final OrganizationRepo organizationRepo;
    private final UserService service;
    private final RoleService roleService;
    private final TaskStatusRepo statusRepo;

    @Override
    public void run(String ...args) throws Exception {

        organizationRepo.deleteAll();
        log.info("Deleted all organizations");

        roleService.saveRole(new Role(null, ROLE_ADMIN));
        roleService.saveRole(new Role(null, ROLE_EMPLOYEE));
        log.info("Added roles to db");

        AppUser user = new AppUser(null, "john", "123Aaa", new ArrayList<>());
        service.saveUser(user);
        service.addRoleToUser("john",ROLE_ADMIN);
        log.info("Created default admin user");

        statusRepo.save(new TaskStatus(null,"assigned"));
        statusRepo.save(new TaskStatus(null,"in_progress"));
        statusRepo.save(new TaskStatus(null,"done"));
        log.info("Added statuses to TaskStatus table");
    }
}
