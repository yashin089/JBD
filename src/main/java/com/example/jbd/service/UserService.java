package com.example.jbd.service;

import com.example.jbd.dto.mapper.TaskGroupMapper;
import com.example.jbd.dto.mapper.TaskMapper;
import com.example.jbd.dto.mapper.UserMapper;
import com.example.jbd.dto.request.UserRequest;
import com.example.jbd.dto.response.entity.TaskGroupResponse;
import com.example.jbd.dto.response.entity.TaskResponse;
import com.example.jbd.dto.response.entity.UserResponse;
import com.example.jbd.dto.response.exception.AlreadyExistsException;
import com.example.jbd.dto.response.exception.NotFoundException;
import com.example.jbd.repository.entity.User;
import com.example.jbd.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TaskMapper taskMapper;
    private final TaskGroupMapper taskGroupMapper;

    public List<UserResponse> findAll() {
        return userMapper.asListResponse(userRepository.findAll());
    }

    public User create(User user) throws AlreadyExistsException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new AlreadyExistsException("Email already exists");
        }
        return userRepository.save(user);
    }

    public void delete(Long id) throws NotFoundException {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException();
        }
        userRepository.deleteById(id);
    }

    @Transactional
    @Modifying
    public UserResponse update(Long id, @Valid UserRequest request) throws NotFoundException, AlreadyExistsException {
        User user = userRepository.findById(id).orElseThrow(NotFoundException::new);
        if (request.email() != null && !request.email().equals(user.getEmail())) {
            if (userRepository.existsByEmail(request.email())) {
                throw new AlreadyExistsException("Email already exists");
            }
        }
        user = userMapper.update(user, request);
        return userMapper.asResponse(user);

//        userRepository.save(user);
    }

    public List<TaskGroupResponse> findAllTaskGroups(Principal principal) throws NotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
        }
        User user = userRepository.findByEmail(principal.getName());
        if (user == null)
            throw new NotFoundException();
        return taskGroupMapper.asListResponse(user.getTaskGroups());
    }

    public List<TaskResponse> findAllTasks(Principal principal) throws NotFoundException {
        User user = userRepository.findByEmail(principal.getName());
        if (user == null)
            throw new NotFoundException();
        return taskMapper.asListResponse(user.getTasks());
    }

}
