package com.learn1.jwt1.repository;

import com.learn1.jwt1.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {


}
