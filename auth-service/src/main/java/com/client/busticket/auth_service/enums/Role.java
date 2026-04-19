package com.client.busticket.auth_service.enums;

import java.util.Set;

public enum Role {
    USER(Set.of(Permissions.APP_READ,Permissions.APP_WRITE)),
    ADMIN(Set.of(Permissions.APP_READ)),
    OPERATOR;
    private final Set<Permissions> permissions;

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }
}
