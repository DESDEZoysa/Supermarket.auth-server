package com.eranga.supermarket.auth_server.model.dto;

import com.eranga.supermarket.auth_server.model.AppEnum.UserRoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppUserDto {

    private Integer id;
    private String userName;
    private String password;
    private UserRoleEnum userRole;
}
