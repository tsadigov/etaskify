package com.tsadigov.etaskify.service;

import com.tsadigov.etaskify.Dto.ResponseDTO;
import com.tsadigov.etaskify.Dto.SignUpDTO;
import com.tsadigov.etaskify.config.MapperConfig;
import com.tsadigov.etaskify.domain.AppUser;
import com.tsadigov.etaskify.domain.Organization;
import com.tsadigov.etaskify.domain.Role;
import com.tsadigov.etaskify.repository.AppUserRepo;
import com.tsadigov.etaskify.repository.OrganizationRepo;
import com.tsadigov.etaskify.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService{

    private final OrganizationRepo organizationRepo;
    private final AppUserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;
    private final MapperConfig mapper;


    @Override
    public Optional<Organization> getOne(Long id) {
        return organizationRepo.findById(id);
    }

    @Override
    public List<Organization> getAll() {
        return organizationRepo.findAll();
    }

    @Override
    @Transactional
    public void signUp(SignUpDTO signUpDTO) {

        // add admin user
        AppUser user = new AppUser(null,signUpDTO.getUsername(),passwordEncoder.encode(signUpDTO.getPassword()),new ArrayList<>());
        userRepo.save(user);

        // add admin role to user
        Role role = roleRepo.findRoleByRoleName("ROLE_ADMIN");
        user.getRoles().add(role);

        // add organization
        Organization organization = new Organization(null, signUpDTO.getOrganizationName(), signUpDTO.getPhoneNumber(), signUpDTO.getAddress(), true);
        organizationRepo.save(organization);

    }
}
