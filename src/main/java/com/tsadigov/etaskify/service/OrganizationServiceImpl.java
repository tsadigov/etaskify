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

    @Override
    public Organization getOne(Long id) {
        return organizationRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(NOT_FOUND_MESSAGE));
    }

    @Override
    public List<Organization> getAll() {
        return organizationRepo.findAll();
    }

}
