package com.example.jbd.service;

import com.example.jbd.dto.mapper.TaskGroupMapper;
import com.example.jbd.dto.mapper.TaskMapper;
import com.example.jbd.dto.request.TaskGroupRequest;
import com.example.jbd.dto.response.entity.TaskGroupResponse;
import com.example.jbd.dto.response.exception.NotFoundException;
import com.example.jbd.repository.TaskGroupRepository;
import com.example.jbd.repository.TaskRepository;
import com.example.jbd.repository.UserRepository;
import com.example.jbd.repository.entity.TaskGroup;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskGroupService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final TaskGroupRepository taskGroupRepository;
    private final TaskMapper taskMapper;
    private final TaskGroupMapper taskGroupMapper;

    public TaskGroupResponse create(TaskGroupRequest taskGroupRequest) throws NotFoundException {
        TaskGroup taskGroup = new TaskGroup();
        taskGroupMapper.update(taskGroup, taskGroupRequest, userRepository);
        taskGroupRepository.save(taskGroup);
        return taskGroupMapper.asResponse(taskGroup);
    }

    public TaskGroupResponse getTaskById(Long id) throws NotFoundException {
        TaskGroup taskGroup = taskGroupRepository.findById(id).orElseThrow(NotFoundException::new);
        return taskGroupMapper.asResponse(taskGroup);
    }

}
