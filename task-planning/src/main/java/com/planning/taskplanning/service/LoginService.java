package com.planning.taskplanning.service;


import com.planning.taskplanning.config.security.Login;
import com.planning.taskplanning.model.Token;
import com.planning.taskplanning.model.User;

public interface LoginService {
    Token authenticate(Login login);
}
