package com.client.busticket.auth_service.records;

import com.client.busticket.auth_service.enums.Role;

public record RegisterRequest(String name, String email, String password, Role role) {
}
