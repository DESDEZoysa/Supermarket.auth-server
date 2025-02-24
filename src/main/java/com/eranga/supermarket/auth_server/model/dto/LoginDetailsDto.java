package com.eranga.supermarket.auth_server.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDetailsDto {

    private String userName;
    private String password;
}
