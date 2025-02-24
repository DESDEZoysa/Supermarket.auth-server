package com.eranga.supermarket.auth_server.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecaptchaDto {

    private Boolean success;
    private String challege_ts;
    private String hostname;
    private Double score;
    private String action;
}
