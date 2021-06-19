package com.planning.taskplanning.service;


import com.planning.taskplanning.config.security.Login;
import com.planning.taskplanning.model.Token;
import com.planning.taskplanning.model.User;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    Token authenticate(Login login);
    User forgotPassword(String question, String answer);
}
