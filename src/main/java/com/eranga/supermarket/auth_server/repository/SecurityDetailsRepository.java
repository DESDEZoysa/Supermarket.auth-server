package com.eranga.supermarket.auth_server.repository;

import com.eranga.supermarket.auth_server.model.entity.SecurityDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

public interface SecurityDetailsRepository extends JpaRepository<SecurityDetailsEntity,Integer> {

    @Override
    @PreAuthorize("hasAuthority('SECURITY_CONFIG')")
    SecurityDetailsEntity save(SecurityDetailsEntity securityDetailsEntity);
}
