package com.sentinelx.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sentinelx.app.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
