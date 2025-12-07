package com.example.jbd.dto.response.entity;

import lombok.Getter;

@Getter
public class TaskGroupResponse extends EntityResponse {
    private final String name;
    private final Long user_id;

    public TaskGroupResponse(Long id, String name, Long user_id) {
        super(id);
        this.name = name;
        this.user_id = user_id;
    }
}