package com.example.jbd.dto.request;

public record SignUpRequest(
        String email,
        String password,
        String name
) {
}
