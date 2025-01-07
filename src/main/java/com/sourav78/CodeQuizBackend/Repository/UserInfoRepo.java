package com.sourav78.CodeQuizBackend.Repository;

import com.sourav78.CodeQuizBackend.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {
}
