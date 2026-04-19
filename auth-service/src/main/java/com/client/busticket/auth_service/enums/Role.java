package com.client.busticket.auth_service.enums;

import lombok.Getter;

import java.util.Set;

@Getter
public enum Role {
    USER(Set.of(Permissions.APP_READ, Permissions.APP_WRITE)),
    ADMIN(Set.of(Permissions.APP_READ, Permissions.APP_WRITE, Permissions.APP_DELETE)),
    OPERATOR(Set.of(Permissions.APP_READ));

    private final Set<Permissions> permissions;

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

}
