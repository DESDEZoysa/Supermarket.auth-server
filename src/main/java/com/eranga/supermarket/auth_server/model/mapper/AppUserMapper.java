package com.eranga.supermarket.auth_server.model.mapper;

import com.eranga.supermarket.auth_server.model.dto.AppUserDto;
import com.eranga.supermarket.auth_server.model.entity.AppUserEntity;
import org.springframework.stereotype.Component;

@Component
public class AppUserMapper {

    public AppUserEntity mapDtoToEntity(AppUserDto appUserDto){
        return AppUserEntity.builder().
                username(appUserDto.getUserName()).
                password(appUserDto.getPassword()).
                userRole(appUserDto.getUserRole()).
                build();
    }
}
