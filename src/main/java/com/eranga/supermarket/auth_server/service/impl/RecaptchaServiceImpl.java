package com.eranga.supermarket.auth_server.service.impl;

import com.eranga.supermarket.auth_server.model.dto.RecaptchaDto;
import com.eranga.supermarket.auth_server.service.RecaptchaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
@Service
public class RecaptchaServiceImpl implements RecaptchaService {

    @Value("${spring.application.security.recaptcha.secretKey}")
    private String secretKey;
    @Value("${spring.application.security.recaptcha.verifyUrl}")
    private String verifyUrl;
    private final RestTemplate restTemplate;

    @Override
    public RecaptchaDto validateToken(String recaptchaToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("secret", secretKey);
        map.add("response",recaptchaToken);
        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(map,headers);
        ResponseEntity<RecaptchaDto> response = this.restTemplate.exchange(this.verifyUrl,
                HttpMethod.POST,
                entity,
                RecaptchaDto.class);
        return response.getBody();
    }
}
