package com.example.kafkasandbox.repository;

import com.example.kafkasandbox.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
