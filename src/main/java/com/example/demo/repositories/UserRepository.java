package com.example.demo.repositories;

import com.example.demo.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("SELECT u FROM UserEntity u WHERE u.user = :user AND u.password = :password")
    UserEntity login(@Param("user") String user, @Param("password") String password);
}
