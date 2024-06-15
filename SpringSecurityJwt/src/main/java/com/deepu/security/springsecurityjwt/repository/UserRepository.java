package com.deepu.security.springsecurityjwt.repository;

import com.deepu.security.springsecurityjwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
