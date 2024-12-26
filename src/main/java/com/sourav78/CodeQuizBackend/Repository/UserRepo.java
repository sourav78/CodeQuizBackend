package com.sourav78.CodeQuizBackend.Repository;

import com.sourav78.CodeQuizBackend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    public User findByUserName(String username);
    public User findByEmail(String email);
}
