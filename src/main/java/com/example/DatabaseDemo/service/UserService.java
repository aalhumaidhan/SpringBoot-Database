package com.example.DatabaseDemo.service;

import com.example.DatabaseDemo.bo.CreateUserRequest;
import com.example.DatabaseDemo.bo.UserResponse;
import com.example.DatabaseDemo.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> getAllUsers();
    UserResponse updateUserStatus(Long id, String status);
    UserResponse createUser(CreateUserRequest request);
    List<UserEntity> searchUser(String status);
}
