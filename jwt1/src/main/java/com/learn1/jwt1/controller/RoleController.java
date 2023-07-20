package com.learn1.jwt1.controller;

import com.learn1.jwt1.dto.request.CreateRoleRequest;
import com.learn1.jwt1.dto.response.ApiResponse;
import com.learn1.jwt1.service.RoleService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/roles")
    public ApiResponse addRole(@RequestBody CreateRoleRequest createRoleRequest){
        log.info("<<<<<< registerUser()");
        Integer savedId = this.roleService.addRole(createRoleRequest);
        log.info("savedId {}",savedId);
        log.info("registerUser() >>>>>");
        return new ApiResponse(savedId, HttpStatus.CREATED.value());
    }
}
