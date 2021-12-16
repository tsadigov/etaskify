package com.tsadigov.etaskify.repository;

import com.tsadigov.etaskify.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepo extends JpaRepository<Organization, Long> {
}
