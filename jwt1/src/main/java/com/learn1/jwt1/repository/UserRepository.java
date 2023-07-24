package com.learn1.jwt1.repository;

import com.learn1.jwt1.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    @Query("SELECT ue FROM UserEntity ue join fetch ue.role join fetch ue.refreshToken WHERE ue.email = :email")
    Optional<UserEntity> getUserByUsernameForRefreshToken(@Param("email") String username);

    @Query("SELECT ue FROM UserEntity ue join fetch ue.role WHERE ue.email = :email")
    Optional<UserEntity> getUserByUsername(@Param("email") String username);


}
