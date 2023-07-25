package com.learn1.jwt1.config;

public class RefreshTokenNote {

    /*
**************************************************************************************************
                          Step of refersh token process
**************************************************************************************************
       1. Create Entity Refresh Token with variable
           public class RefreshTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String refreshToken;
    private Instant expiryDate;

    @OneToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private UserEntity user;

}
**************************************************************************************************
    step 2 : Create repository for RefreshTokenEntity
****************************************************************************************************

    * Optional<RefreshTokenEntity> findByrefreshToken(String refershToken);
    *
**************************************************************************************************
    step 3 : Create service for RefreshTokenEntity
***************************************************************************************************
    *  package com.learn1.jwt1.service;

import com.learn1.jwt1.entity.RefreshTokenEntity;
import com.learn1.jwt1.entity.UserEntity;
import com.learn1.jwt1.repository.RefreshTokenRepository;
import com.learn1.jwt1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenService {


    private final RefreshTokenRepository refreshTokenRepository;

    private final UserService userService;

    private final UserRepository userRepository;



     @Transactional
    public RefreshTokenEntity creatRefreshToken(String username){
        //UserEntity userByName = this.userService.getUserByName(username);
         log.debug("<<<<<<<<< saveBook()");
         log.debug("saveBook() >>>>>>>");
         Optional<UserEntity> userByUsernameForRefreshToken = this.userRepository.getUserByUsernameForRefreshToken(username);
         RefreshTokenEntity refreshToken1 = userByUsernameForRefreshToken.get().getRefreshToken();
        if(refreshToken1==null){
            RefreshTokenEntity refreshToken = new RefreshTokenEntity();
            refreshToken.setRefreshToken(UUID.randomUUID().toString());
            refreshToken.setExpiryDate(Instant.now().plusMillis(1200000));//1200000 20 minute
            refreshToken.setUser(this.userService.getUserByName(username));
             refreshToken1=this.refreshTokenRepository.save(refreshToken);
        }
       else{
            refreshToken1.setExpiryDate(Instant.now().plusMillis(1700000));
            log.info("expireDate {}", Instant.now().plusMillis(1700000));
        }

        return refreshToken1;
    }

    public RefreshTokenEntity verifyRefreshToken(String refreshToken){
        log.debug("<<<<<<<<< verifyRefreshToken()");
        RefreshTokenEntity  refreshTokenEntity = this.refreshTokenRepository.findByrefreshToken(refreshToken).orElseThrow(() -> new RuntimeException("Given Token does not exist"));
        if(refreshTokenEntity.getExpiryDate().compareTo(Instant.now())<0){
            this.refreshTokenRepository.delete(refreshTokenEntity);
            throw new RuntimeException("refreshToken is expired");
        }
        log.debug("verifyRefreshToken() >>>>>>>");
        return refreshTokenEntity;
    }


}
***************************************************************************************************
                                 step 4: In login to write code to generate refresh token
*****    **************************************************************************************************
    *
    * String token = this.jwtUtil.generateToken(userLoginRequest.getEmail());
        RefreshTokenEntity refreshTokenEntity = this.refreshTokenService.creatRefreshToken(userLoginRequest.getEmail());

*************************************************************************************************
                    step 5 : to refresh Jwt Token
**************************************************************************************************
         @PostMapping("/refreshJwtToken")
    public ApiResponse refreshJwtToken(@RequestBody RefreshJwtRequest refreshJwtRequest){
        RefreshTokenEntity refreshTokenEntity = this.refreshTokenService.verifyRefreshToken(refreshJwtRequest.getRefreshToken());
        UserEntity user = refreshTokenEntity.getUser();
        String refreshJwttoken = this.jwtUtil.generateToken(user.getEmail());
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setJwtToken(refreshJwttoken);
        jwtResponse.setRefershToken(refreshTokenEntity.getRefreshToken());
        return new ApiResponse(jwtResponse,HttpStatus.OK.value());
    }



     */


}
