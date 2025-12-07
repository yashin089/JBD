package com.example.jbd.dto.mapper;

import com.example.jbd.dto.request.TaskRequest;
import com.example.jbd.dto.response.entity.TaskResponse;
import com.example.jbd.dto.response.exception.NotFoundException;
import com.example.jbd.repository.TaskGroupRepository;
import com.example.jbd.repository.UserRepository;
import com.example.jbd.repository.entity.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapper {
    public TaskResponse asResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getName(),
                task.getUser().getId(),
                task.getTaskGroup().getId()
        );
    }

    public Task update(
            Task task,
            TaskRequest request,
            UserRepository userRepository,
            TaskGroupRepository taskGroupRepository
    ) throws NotFoundException {
        task.setName(request.name());
        task.setUser(userRepository.findById(request.user_id()).orElseThrow(NotFoundException::new));
        task.setTaskGroup(taskGroupRepository.findById(request.group_id()).orElseThrow(NotFoundException::new));
        return task;
    }

    public List<TaskResponse> asListResponse(Iterable<Task> tasks) {
        List<TaskResponse> response = new ArrayList<>();
        for (Task task : tasks) {
            response.add(asResponse(task));
        }
        return response;
    }
}
