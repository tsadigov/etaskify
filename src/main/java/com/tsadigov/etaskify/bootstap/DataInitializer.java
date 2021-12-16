package com.tsadigov.etaskify.bootstap;

import com.tsadigov.etaskify.Dto.LoginDTO;
import com.tsadigov.etaskify.config.MapperConfig;
import com.tsadigov.etaskify.domain.AppUser;
import com.tsadigov.etaskify.domain.Organization;
import com.tsadigov.etaskify.domain.Role;
import com.tsadigov.etaskify.repository.OrganizationRepo;
import com.tsadigov.etaskify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {


    private final OrganizationRepo organizationRepo;
    private final UserService service;

    @Override
    public void run(String ...args) throws Exception {

        organizationRepo.deleteAll();

        service.saveRole(new Role(null, "ROLE_ADMIN"));
        service.saveRole(new Role(null, "ROLE_EMPLOYEE"));

        AppUser user = new AppUser(null, "john", "123", new ArrayList<>());
        service.saveUser(user);

        service.addRoleToUser("john","ROLE_ADMIN");

//        Organization organizationA = new Organization(null,"Company A","0515802251","",true);
//        organizationRepo.save(organizationA);


        organizationRepo.findAll().forEach(book -> {
            System.out.println("Book id: "+book.getId());
            System.out.println("Book title: "+book.getOrganizationName());
        });
    }
}
