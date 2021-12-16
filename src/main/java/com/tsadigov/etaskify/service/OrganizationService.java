package com.tsadigov.etaskify.service;

import com.tsadigov.etaskify.Dto.SignUpDTO;
import com.tsadigov.etaskify.domain.Organization;

import java.util.List;
import java.util.Optional;

public interface OrganizationService {
    Optional<Organization> getOne(Long id);
    List<Organization> getAll();
    void signUp(SignUpDTO signUpDTO);
}
