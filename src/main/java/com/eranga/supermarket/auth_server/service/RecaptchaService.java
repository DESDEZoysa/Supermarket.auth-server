package com.eranga.supermarket.auth_server.service;

import com.eranga.supermarket.auth_server.model.dto.RecaptchaDto;

public interface RecaptchaService {

    RecaptchaDto validateToken(String recaptchaToken);
}
