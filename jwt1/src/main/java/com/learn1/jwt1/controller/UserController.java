package com.learn1.jwt1.controller;


import com.learn1.jwt1.dto.request.RefreshJwtRequest;
import com.learn1.jwt1.dto.request.UserLoginRequest;
import com.learn1.jwt1.dto.request.UserRegisterRequest;
import com.learn1.jwt1.dto.response.ApiResponse;
import com.learn1.jwt1.dto.response.JwtResponse;
import com.learn1.jwt1.entity.RefreshTokenEntity;
import com.learn1.jwt1.entity.UserEntity;
import com.learn1.jwt1.service.RefreshTokenService;
import com.learn1.jwt1.service.UserService;
import com.learn1.jwt1.util.JwtUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name = "jwtBearerAuth")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    private final UserService userService;

    private final RefreshTokenService refreshTokenService;

    @PostMapping(value = "/register")
    public ResponseEntity<Integer> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        log.info("<<<<<<<<< register()");
        Integer userId = this.userService.registerUser(userRegisterRequest);
        log.info("saved userId {}",userId);
        log.info("register() >>>>>>>");
        return ResponseEntity.ok(userId);
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public String getName() {
        return "rushikesh";
    }

    @PostMapping("/login")
    public ApiResponse<String> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
           log.info("<<<<<<<<< loginUser()");
        // authenticate user using authentication manager with help of email and password

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userLoginRequest.getEmail(), userLoginRequest.getPassword());

        /**
         * This will throw AuthenticationException if authentication failed which will be handled by our
         * AuthenticationEntryPointJwt class , we can also handle with global exception handler
         */
        Authentication authenticate = this.authenticationManager.authenticate(authentication);


        String token = this.jwtUtil.generateToken(userLoginRequest.getEmail());
        RefreshTokenEntity refreshTokenEntity = this.refreshTokenService.creatRefreshToken(userLoginRequest.getEmail());

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setJwtToken(token);
        jwtResponse.setRefershToken(refreshTokenEntity.getRefreshToken());
        log.debug("loginUser() >>>>>>>");
        return new ApiResponse(jwtResponse, HttpStatus.OK.value());

    }

    @PostMapping("/refreshJwtToken")
    public ApiResponse refreshJwtToken(@RequestBody RefreshJwtRequest refreshJwtRequest) {
        log.info("<<<<<<<<< refreshJwtToken()");
        RefreshTokenEntity refreshTokenEntity = this.refreshTokenService.verifyRefreshToken(refreshJwtRequest.getRefreshToken());
        UserEntity user = refreshTokenEntity.getUser();
        String refreshJwttoken = this.jwtUtil.generateToken(user.getEmail());
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setJwtToken(refreshJwttoken);
        jwtResponse.setRefershToken(refreshTokenEntity.getRefreshToken());
        log.info("refreshJwtToken() >>>>>>>");
        return new ApiResponse(jwtResponse, HttpStatus.OK.value());
    }


    @GetMapping("/t")
    public String getTest() {
        return "t";
    }

    @GetMapping("/customer")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getCustomer() {
        return "customer";
    }


}
