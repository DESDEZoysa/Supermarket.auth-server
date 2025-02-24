package com.eranga.supermarket.auth_server.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationDto {

    private String accessToken;
    private String refreshToken;
}
