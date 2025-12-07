package com.example.jbd.controller;

import com.example.jbd.dto.request.TaskGroupRequest;
import com.example.jbd.dto.request.TaskRequest;
import com.example.jbd.dto.request.UserRequest;
import com.example.jbd.dto.response.entity.TaskGroupResponse;
import com.example.jbd.dto.response.entity.TaskResponse;
import com.example.jbd.dto.response.entity.UserResponse;
import com.example.jbd.dto.response.exception.AlreadyExistsException;
import com.example.jbd.dto.response.exception.NotFoundException;
import com.example.jbd.service.TaskGroupService;
import com.example.jbd.service.TaskService;
import com.example.jbd.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/user")
public class UserController {

    private final UserService userService;
    private final TaskService taskService;
    private final TaskGroupService taskGroupService;

    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    @GetMapping(path = "/task_groups")
    public List<TaskGroupResponse> findAllTaskGroups(Principal principal) throws NotFoundException {
        return userService.findAllTaskGroups(principal);
    }

    @GetMapping(path = "/task_group/{id}")
    public TaskGroupResponse findTaskGroup(@PathVariable Long id, Principal principal) throws NotFoundException {
        return taskGroupService.getTaskById(id);
    }

    @GetMapping(path = "/tasks")
    public List<TaskResponse> findAllTasks(Principal principal) throws NotFoundException {
        return userService.findAllTasks(principal);
    }

    @PostMapping("/task_group")
    public TaskGroupResponse createTaskGroup(@RequestBody TaskGroupRequest taskGroupRequest) throws NotFoundException {
        return taskGroupService.create(taskGroupRequest);
    }

    @PostMapping("/task")
    public TaskResponse createTask(@RequestBody TaskRequest taskRequest) throws NotFoundException {
        return taskService.create(taskRequest);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable Long id) throws NotFoundException {
        userService.delete(id);
    }

    @PutMapping(path = "{id}")
    public UserResponse update(@PathVariable Long id, @RequestBody UserRequest request) throws NotFoundException, AlreadyExistsException {
        return userService.update(id, request);
    }
}
