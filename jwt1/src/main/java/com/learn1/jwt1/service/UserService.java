package com.learn1.jwt1.service;

import com.learn1.jwt1.dto.request.UserRegisterRequest;
import com.learn1.jwt1.entity.RoleEntity;
import com.learn1.jwt1.entity.UserEntity;
import com.learn1.jwt1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Integer registerUser(UserRegisterRequest userRegisterRequest){
        log.info("<<<<<< registerUser()");
        String encodePassword = this.passwordEncoder.encode(userRegisterRequest.getPassword());
        RoleEntity roleEntity = this.roleService.fetchRoleById(userRegisterRequest.getRoleId());
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstname(userRegisterRequest.getFirstname());
        userEntity.setLastname(userRegisterRequest.getFirstname());
        userEntity.setEmail(userRegisterRequest.getEmail());
        userEntity.setPassword(encodePassword);
        userEntity.setRole(roleEntity);
        UserEntity registerdUser = this.userRepository.save(userEntity);
         log.debug("savedUser",registerdUser);
        log.info("registerUser() >>>>>");
         return registerdUser.getId();

    }

}
