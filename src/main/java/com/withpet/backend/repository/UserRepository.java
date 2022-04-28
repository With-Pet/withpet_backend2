package com.withpet.backend.repository;

import com.withpet.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findBySnsId(String email);
}

