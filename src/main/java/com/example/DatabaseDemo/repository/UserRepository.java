package com.example.DatabaseDemo.repository;

import com.example.DatabaseDemo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
