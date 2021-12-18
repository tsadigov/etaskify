package com.tsadigov.etaskify.service;

import com.tsadigov.etaskify.dto.SignUpDTO;
import com.tsadigov.etaskify.domain.Organization;

import java.util.List;

public interface OrganizationService {
    Organization getOne(Long id);
    List<Organization> getAll();
}
