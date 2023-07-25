package com.learn1.jwt1.service;

import com.learn1.jwt1.Exception.CustomException;
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

         Optional<UserEntity> userByUsernameForRefreshToken = this.userRepository.getUserByUsernameForRefreshToken(username);
         RefreshTokenEntity refreshToken1 = userByUsernameForRefreshToken.get().getRefreshToken();
         log.info("refreshTokenEntity {}",refreshToken1);
        if(refreshToken1==null){
            RefreshTokenEntity refreshToken = new RefreshTokenEntity();
            refreshToken.setRefreshToken(UUID.randomUUID().toString());
            refreshToken.setExpiryDate(Instant.now().plusMillis(1200000));//1200000 20 minute
            refreshToken.setUser(this.userService.getUserByName(username));
             return this.refreshTokenRepository.save(refreshToken);
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
            throw new CustomException("refreshToken is expired please login ");
        }
        log.debug("verifyRefreshToken() >>>>>>>");
        return refreshTokenEntity;
    }


}
