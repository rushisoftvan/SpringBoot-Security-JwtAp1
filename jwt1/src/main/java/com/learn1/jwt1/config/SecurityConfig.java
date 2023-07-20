package com.learn1.jwt1.config;

import com.learn1.jwt1.Filter.SecurityFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

      private final AuthenticationEntryPoint authenticationEntryPoint;

      private final SecurityFilter securityFilter;
    public static final String[] PUBLIC_URLS = {
            "/register"

    };
    public static final String[] PUBLIC_URL = {"/register", "/login"};

    @Bean
    /**
     * We need this bean that's why creating
     * here we are using stateless authentication so we need to create object of AuthenticationManager
     */
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /**
         * Need to disable csrf otherwise we will get 403 or 401
         * on public POST types urls
         */
        return http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(PUBLIC_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
                /**
                 * We have to set this authenticationEntryPoint otherwise we will get 403 instead of 401 if login cred invalid
                 */
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
               .and()
                .addFilterBefore(securityFilter , UsernamePasswordAuthenticationFilter.class)
                .build();
    }


}
