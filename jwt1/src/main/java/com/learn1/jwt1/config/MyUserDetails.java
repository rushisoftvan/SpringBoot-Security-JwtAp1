package com.learn1.jwt1.config;

import com.learn1.jwt1.dto.JpaGrantedAuthority;
import com.learn1.jwt1.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class MyUserDetails implements UserDetails {
    private final UserEntity userEntity;
    private final List<JpaGrantedAuthority> jpaGrantedAuthorityList;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return jpaGrantedAuthorityList;
    }

    @Override
    public String getPassword() {
       return  userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
