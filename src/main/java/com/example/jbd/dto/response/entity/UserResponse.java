package com.example.jbd.dto.response.entity;

import com.example.jbd.repository.entity.Role;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponse extends EntityResponse {
    private final String name;
    private final String email;
    private final Role role;

    public UserResponse(Long id, String name, String email, Role role) {
        super(id);
        this.name = name;
        this.email = email;
        this.role = role;
    }
}
