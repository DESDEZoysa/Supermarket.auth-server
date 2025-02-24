package com.eranga.supermarket.auth_server.service.impl;

import com.eranga.supermarket.auth_server.model.dto.UserDetailDto;
import com.eranga.supermarket.auth_server.model.entity.AppUserEntity;
import com.eranga.supermarket.auth_server.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUserEntity appUserEntity = appUserRepository.findByUsername(username);
        if(appUserEntity == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new UserDetailDto(appUserEntity);
    }
}
