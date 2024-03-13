package com.allasassis.rabbitmqapp.repository;

import com.allasassis.rabbitmqapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
