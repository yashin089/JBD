package com.example.jbd.dto.request;

public record TaskRequest(
        String name,
        Long user_id,
        Long group_id
) {
}
