package com.example.jbd.dto.response.entity;

import lombok.Getter;

@Getter
public class TaskResponse extends EntityResponse {
    private final String name;
    private final Long user_id;
    private final Long group_id;

    public TaskResponse(Long id, String name, Long user_id, Long group_id) {
        super(id);
        this.name = name;
        this.user_id = user_id;
        this.group_id = group_id;
    }
}