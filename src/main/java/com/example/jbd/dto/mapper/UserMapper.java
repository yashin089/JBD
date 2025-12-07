package com.example.jbd.dto.mapper;

import com.example.jbd.dto.request.UserRequest;
import com.example.jbd.dto.response.entity.UserResponse;
import com.example.jbd.repository.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public UserResponse asResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }

    public User update(User user, UserRequest request) {
        user.setEmail(request.email());
        user.setName(request.name());

        return user;
    }

    public List<UserResponse> asListResponse(Iterable<User> users) {
        List<UserResponse> response = new ArrayList<>();
        for (User user : users) {
            response.add(asResponse(user));
        }
        return response;
    }
}
