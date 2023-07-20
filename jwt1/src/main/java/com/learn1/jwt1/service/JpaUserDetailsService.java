package com.learn1.jwt1.service;

import com.learn1.jwt1.config.MyUserDetails;
import com.learn1.jwt1.dto.JpaGrantedAuthority;
import com.learn1.jwt1.entity.UserEntity;
import com.learn1.jwt1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.getUserByUsername(email).orElseThrow(() -> new UsernameNotFoundException("user does not exixts"));
        log.info("<<<<<< loadUserByUsername()");

       // log.info("loadUserByUsername() >>>>>");
      //  return new JpaUserDetails(userEntity, List.of(jpaGrantedAuthority));

        JpaGrantedAuthority jpaGrantedAuthority = new JpaGrantedAuthority(userEntity.getRole());
        return new MyUserDetails(userEntity, List.of(jpaGrantedAuthority));
    }

      
    
}
