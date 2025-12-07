package com.example.jbd.service;

import com.example.jbd.dto.mapper.TaskMapper;
import com.example.jbd.dto.request.TaskRequest;
import com.example.jbd.dto.response.entity.TaskResponse;
import com.example.jbd.dto.response.exception.NotFoundException;
import com.example.jbd.repository.TaskGroupRepository;
import com.example.jbd.repository.TaskRepository;
import com.example.jbd.repository.UserRepository;
import com.example.jbd.repository.entity.Task;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final TaskGroupRepository taskGroupRepository;
    private final TaskMapper taskMapper;

    public TaskResponse create(TaskRequest taskRequest) throws NotFoundException {
        Task task = new Task();
        taskMapper.update(task, taskRequest, userRepository, taskGroupRepository);
        taskRepository.save(task);
        return taskMapper.asResponse(task);
    }

    public TaskResponse getTaskById(Long id) throws NotFoundException {
        Task task = taskRepository.findById(id).orElseThrow(NotFoundException::new);
        return taskMapper.asResponse(task);
    }
}
