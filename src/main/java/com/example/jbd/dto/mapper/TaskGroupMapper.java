package com.example.jbd.dto.mapper;

import com.example.jbd.dto.request.TaskGroupRequest;
import com.example.jbd.dto.response.entity.TaskGroupResponse;
import com.example.jbd.dto.response.exception.NotFoundException;
import com.example.jbd.repository.UserRepository;
import com.example.jbd.repository.entity.TaskGroup;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskGroupMapper {
    public TaskGroupResponse asResponse(TaskGroup taskGroup) {
        return new TaskGroupResponse(
                taskGroup.getId(),
                taskGroup.getName(),
                taskGroup.getUser().getId()
        );
    }

    public TaskGroup update(
            TaskGroup taskGroup,
            TaskGroupRequest request,
            UserRepository userRepository
    ) throws NotFoundException {
        taskGroup.setName(request.name());
        taskGroup.setUser(userRepository.findById(request.user_id()).orElseThrow(NotFoundException::new));
        return taskGroup;
    }

    public List<TaskGroupResponse> asListResponse(Iterable<TaskGroup> taskGroups) {
        List<TaskGroupResponse> response = new ArrayList<>();
        for (TaskGroup taskGroup : taskGroups) {
            response.add(asResponse(taskGroup));
        }
        return response;
    }
}
