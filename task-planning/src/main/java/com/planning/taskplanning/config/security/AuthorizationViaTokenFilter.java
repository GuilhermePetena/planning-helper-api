package com.planning.taskplanning.config.security;

import com.planning.taskplanning.model.User;
import com.planning.taskplanning.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class AuthorizationViaTokenFilter extends OncePerRequestFilter {

    private TokenUtil tokenUtil;
    private UserRepository userRepository;

    public AuthorizationViaTokenFilter(TokenUtil tokenUtil, UserRepository userRepository) {
        this.tokenUtil = tokenUtil;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(httpServletRequest);
        boolean valid = tokenUtil.isTokenValid(token);

        if(valid){ authenticateUser(token); }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void authenticateUser(String token) {
        String userId = tokenUtil.getId(token);
        User user = userRepository.findById(userId).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recoverToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(Objects.isNull(token) || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }
        return token.substring(7, token.length());
    }
}
