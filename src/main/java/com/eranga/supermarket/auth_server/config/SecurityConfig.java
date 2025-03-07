package com.eranga.supermarket.auth_server.config;

import com.eranga.supermarket.auth_server.filter.JwtAuthenticationFilter;
import com.eranga.supermarket.auth_server.filter.RateLimitFilter;
import com.eranga.supermarket.auth_server.filter.RecaptchaFilter;
import com.eranga.supermarket.auth_server.model.AppEnum.PermissionEnum;
import com.eranga.supermarket.auth_server.model.AppEnum.UserRoleEnum;
import com.eranga.supermarket.auth_server.service.impl.AppUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AppUserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//    private final RateLimitFilter rateLimitFilter;
//    private final RecaptchaFilter recaptchaFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        System.out.println("securityFilterChain");
        return httpSecurity.
                csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request->request
                        .requestMatchers( "/auth/login","/auth/register").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/admin/**").hasAuthority(PermissionEnum.ADMIN_DELETE.name())
                        .requestMatchers(HttpMethod.POST,"/admin/**").hasAuthority(PermissionEnum.ADMIN_CREATE.name())
                        .requestMatchers(HttpMethod.PUT,"/admin/**").hasAuthority(PermissionEnum.ADMIN_UPDATE.name())
                        .requestMatchers(HttpMethod.DELETE,"/**").hasAnyAuthority(PermissionEnum.DELETE.name())
                        .requestMatchers(HttpMethod.PUT,"/**").hasAnyAuthority(PermissionEnum.UPDATE.name())
                        .requestMatchers(HttpMethod.POST,"/**").hasAnyAuthority(PermissionEnum.CREATE.name())
                        .requestMatchers("/admin/**").hasAnyRole(UserRoleEnum.SUPER_ADMIN.name(),UserRoleEnum.ADMIN.name())
                        .requestMatchers("/manager/**").hasAnyRole(UserRoleEnum.SUPER_ADMIN.name(),UserRoleEnum.ADMIN.name(), UserRoleEnum.MANAGER.name())
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterAfter(recaptchaFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterAfter(rateLimitFilter, RecaptchaFilter.class)
//                .oauth2Client(Customizer.withDefaults())
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(10));
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
