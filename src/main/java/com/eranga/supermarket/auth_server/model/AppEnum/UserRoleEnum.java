package com.eranga.supermarket.auth_server.model.AppEnum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum UserRoleEnum {

    SUPER_ADMIN(
            Set.of(
                    PermissionEnum.READ,
                    PermissionEnum.CREATE,
                    PermissionEnum.UPDATE,
                    PermissionEnum.DELETE,
                    PermissionEnum.ADMIN_CREATE,
                    PermissionEnum.ADMIN_UPDATE,
                    PermissionEnum.ADMIN_DELETE,
                    PermissionEnum.SECURITY_CONFIG
            )
    ),
    ADMIN(
            Set.of(
                    PermissionEnum.READ,
                    PermissionEnum.CREATE,
                    PermissionEnum.UPDATE,
                    PermissionEnum.DELETE
            )
    ),
    MANAGER(
            Set.of(
                    PermissionEnum.READ,
                    PermissionEnum.CREATE,
                    PermissionEnum.UPDATE
            )
    ),
    USER(
            Set.of(
                    PermissionEnum.READ,
                    PermissionEnum.CREATE
            )
    );


    private final Set<PermissionEnum> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
