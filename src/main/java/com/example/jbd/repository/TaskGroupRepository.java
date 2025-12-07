package com.example.jbd.repository;

import com.example.jbd.repository.entity.TaskGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskGroupRepository extends JpaRepository<TaskGroup, Long> {
}
