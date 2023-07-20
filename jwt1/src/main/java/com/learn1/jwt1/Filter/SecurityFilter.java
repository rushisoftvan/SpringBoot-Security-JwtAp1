package com.learn1.jwt1.Filter;

import com.learn1.jwt1.service.JpaUserDetailsService;
import com.learn1.jwt1.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private  final JwtUtil jwtUtil;
    
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
          //Read token from Authentication header
        String token = request.getHeader("Authorization");
        if(token!=null){
            //do validation token

            String accessToken = token.split(" ")[1];
            String username = jwtUtil.getUsername(accessToken);

            //username should not be empty and security context should be   null
            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                boolean isValid = jwtUtil.validateToken(accessToken, userDetails.getUsername());
                if(isValid){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(),userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                }
            }

        }
        filterChain.doFilter(request,response);
    }
}
