package com.learn1.jwt1.repository;

import com.learn1.jwt1.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity,Integer> {

  Optional<RefreshTokenEntity> findByrefreshToken(String refershToken);
}
