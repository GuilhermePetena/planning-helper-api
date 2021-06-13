package com.planning.taskplanning.service.impl;

import com.planning.taskplanning.config.security.Login;
import com.planning.taskplanning.config.security.TokenUtil;
import com.planning.taskplanning.model.Token;
import com.planning.taskplanning.model.User;
import com.planning.taskplanning.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public Token authenticate(Login login) {
            UsernamePasswordAuthenticationToken loginData = login.converte();
            Authentication authentication = authenticationManager.authenticate(loginData);
            String token = tokenUtil.tokenGenerate(authentication);
            return new Token(token, "Bearer");
    }
}
