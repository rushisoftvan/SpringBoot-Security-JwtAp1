package com.learn1.jwt1.controller;


import com.learn1.jwt1.dto.request.UserLoginRequest;
import com.learn1.jwt1.dto.request.UserRegisterRequest;
import com.learn1.jwt1.dto.response.ApiResponse;
import com.learn1.jwt1.service.UserService;
import com.learn1.jwt1.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    private final UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<Integer> register(@RequestBody UserRegisterRequest userRegisterRequest){
        Integer userId = this.userService.registerUser(userRegisterRequest);
        return ResponseEntity.ok(userId);
    }

    @GetMapping("/test")
    public String getName(){
        return "rushikesh";
    }

    @PostMapping("/login")
    public ApiResponse<String> loginUser(@RequestBody UserLoginRequest userLoginRequest){

        // authenticate user using authentication manager with help of email and password

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userLoginRequest.getEmail(), userLoginRequest.getPassword());

        /**
         * This will throw AuthenticationException if authentication failed which will be handled by our
         * AuthenticationEntryPointJwt class , we can also handle with global exception handler
         */
        Authentication authenticate = this.authenticationManager.authenticate(authentication);
        String token = this.jwtUtil.generateToken(userLoginRequest.getEmail());

        return new ApiResponse(token, HttpStatus.OK.value());

    }

    @GetMapping("/t")
    public String getTest(){
        return "t";
    }
}
