package com.eranga.supermarket.auth_server.model.AppEnum;

import lombok.Getter;

@Getter
public enum PermissionEnum {

    READ,
    CREATE,
    UPDATE,
    DELETE,
    ADMIN_CREATE,
    ADMIN_UPDATE,
    ADMIN_DELETE,
    SECURITY_CONFIG

}
