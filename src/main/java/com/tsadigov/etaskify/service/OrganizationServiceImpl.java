package com.tsadigov.etaskify.service;

import com.tsadigov.etaskify.dto.SignUpDTO;
import com.tsadigov.etaskify.domain.AppUser;
import com.tsadigov.etaskify.domain.Employee;
import com.tsadigov.etaskify.domain.Organization;
import com.tsadigov.etaskify.domain.Role;
import com.tsadigov.etaskify.exception.ResourceNotFoundException;
import com.tsadigov.etaskify.repository.EmployeeRepo;
import com.tsadigov.etaskify.repository.AppUserRepo;
import com.tsadigov.etaskify.repository.OrganizationRepo;
import com.tsadigov.etaskify.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.tsadigov.etaskify.bootstap.Constants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepo organizationRepo;
    private final AppUserRepo userRepo;
    private final EmployeeRepo userDetailsRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;


    @Override
    public Organization getOne(Long id) {
        return organizationRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(NOT_FOUND_MESSAGE));
    }

    @Override
    public List<Organization> getAll() {
        return organizationRepo.findAll();
    }

    @Override
    @Transactional
    public Organization signUp(SignUpDTO signUpDTO) {

        // add admin user
        AppUser user = new AppUser();
        user.setUsername(signUpDTO.getUsername());
        user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        userRepo.save(user);

        // save user details

        Employee employee = new Employee();
        employee.setEmail(signUpDTO.getEmail());
        employee.setUserFk(user);
        userDetailsRepo.save(employee);

        // add admin role to user
        Role role = roleRepo.findRoleByRoleName(ROLE_ADMIN);
        log.info("--ROLE {}", role);
        try {
            user.getRoles().add(role);
        }
        catch (Exception ex){
            log.error(ex.getMessage());
        }
        // add organization
        Organization organization = new Organization();
        organization.setOrganizationName(signUpDTO.getOrganizationName());
        organization.setPhoneNumber(signUpDTO.getPhoneNumber());
        organization.setAddress(signUpDTO.getAddress());
        organization.setStatus(true);
        organizationRepo.save(organization);

        return organization;
    }
}
