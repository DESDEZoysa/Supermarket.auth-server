package com.eranga.supermarket.auth_server.model.dto;

import com.eranga.supermarket.auth_server.model.entity.AppUserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailDto implements UserDetails {

    private final AppUserEntity appUserEntity;

    public UserDetailDto(AppUserEntity appUserEntity) {
        this.appUserEntity = appUserEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+this.appUserEntity.getUserRole().name()));
    }

    @Override
    public String getPassword() {
        return this.appUserEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return this.appUserEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
