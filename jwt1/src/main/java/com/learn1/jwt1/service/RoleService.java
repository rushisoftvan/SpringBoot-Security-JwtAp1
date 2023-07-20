package com.learn1.jwt1.service;

import com.learn1.jwt1.Exception.ResourceNotFoundException;
import com.learn1.jwt1.dto.request.CreateRoleRequest;
import com.learn1.jwt1.entity.RoleEntity;
import com.learn1.jwt1.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleService {

    private  final RoleRepository roleRepository;
    public Integer addRole(CreateRoleRequest createRoleRequest){
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(createRoleRequest.getName());
        RoleEntity savedData = this.roleRepository.save(roleEntity);
        log.debug("saved Role Data {}",savedData);
        return savedData.getId();
    }

    public RoleEntity fetchRoleById(Integer roleId){
       return  this.roleRepository.findById(roleId).orElseThrow(()->new ResourceNotFoundException("Role Does Not Exists : "+ roleId));

    }
}
