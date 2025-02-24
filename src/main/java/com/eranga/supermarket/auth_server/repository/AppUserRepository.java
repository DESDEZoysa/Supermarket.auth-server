package com.eranga.supermarket.auth_server.repository;

import com.eranga.supermarket.auth_server.model.entity.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUserEntity,Integer> {

    AppUserEntity findByUsername(String username);
}
