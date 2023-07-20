package com.learn1.jwt1.dto;

import com.learn1.jwt1.entity.RoleEntity;
import com.learn1.jwt1.repository.RoleRepository;
import com.learn1.jwt1.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class JpaGrantedAuthority implements GrantedAuthority {

  private final RoleEntity roleEntity;
    @Override
    public String getAuthority() {
        return this.roleEntity.getName();
    }
}
