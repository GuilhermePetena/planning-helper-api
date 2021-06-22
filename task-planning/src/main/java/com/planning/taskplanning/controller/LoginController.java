package com.planning.taskplanning.controller;

import com.planning.taskplanning.config.security.Login;
import com.planning.taskplanning.model.Token;
import com.planning.taskplanning.model.User;
import com.planning.taskplanning.service.LoginService;
import com.planning.taskplanning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;


@RestController
public class LoginController {

    private final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;


    @PostMapping("/auth")
    public ResponseEntity<Token> autenticar(@RequestBody Login login){
        return ResponseEntity.ok(loginService.authenticate(login));
    }

    @GetMapping("/forgot")
    public ResponseEntity<?> forgotPassword(@RequestParam String question, @RequestParam String answer) {
        log.debug("REST request when the user forget the password : {}", question, answer);
        User user = loginService.forgotPassword(question, answer);
        if(Objects.isNull(user)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userService.converteToDTO(user));
    }
}
