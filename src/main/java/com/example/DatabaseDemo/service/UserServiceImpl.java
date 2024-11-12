package com.example.DatabaseDemo.service;

import com.example.DatabaseDemo.bo.CreateUserRequest;
import com.example.DatabaseDemo.bo.UserResponse;
import com.example.DatabaseDemo.entity.Status;
import com.example.DatabaseDemo.entity.UserEntity;
import com.example.DatabaseDemo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(request.getName());
        userEntity.setStatus(Status.valueOf(request.getStatus()));
        userEntity = userRepository.save(userEntity);
        UserResponse response = new UserResponse(userEntity.getId(), userEntity.getName(), userEntity.getStatus().toString());
        return response;
    }

    @Override
    public UserResponse updateUserStatus(Long id, String status) {
        if (status.equalsIgnoreCase("Active") || status.equalsIgnoreCase("Inactive")) {
            UserEntity userEntity = userRepository.findById(id).orElse(null);
            userEntity.setStatus(Status.valueOf(status.toUpperCase()));
            userEntity = userRepository.save(userEntity);
            UserResponse response = new UserResponse(userEntity.getId(), userEntity.getName(), userEntity.getStatus().toString());
            return response;
        }
        else {
            throw new IllegalArgumentException("Invalid status. Status should only be ACTIVE, or INACTIVE");
        }
    }

    @Override
    public List<UserEntity> searchUser(String status) {
        List<UserEntity> filteredUsers = new ArrayList<>();
        for (UserEntity userEntity : userRepository.findAll()) {
            if (userEntity.getStatus().toString().equalsIgnoreCase(status.toUpperCase())) {
                filteredUsers.add(userEntity);
            }
        }
        return filteredUsers;
    }
}
