package com.tsadigov.etaskify.service;

import com.tsadigov.etaskify.dto.SignUpDTO;
import com.tsadigov.etaskify.domain.Organization;

import java.util.List;
import java.util.Optional;

public interface OrganizationService {
    Optional<Organization> getOne(Long id);
    List<Organization> getAll();
    Organization signUp(SignUpDTO signUpDTO);
}
