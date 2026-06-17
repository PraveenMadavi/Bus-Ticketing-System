package com.client.busticket.auth_service.records;

public record LoginResponse(String token, String message, String userName, String role) {
}
