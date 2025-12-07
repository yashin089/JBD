package com.example.jbd.dto.response.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public abstract class EntityResponse {
    protected Long id;
}
