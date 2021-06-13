package com.planning.taskplanning.controller;

import com.planning.taskplanning.config.security.TokenUtil;
import com.planning.taskplanning.config.security.Login;
import com.planning.taskplanning.model.Token;
import com.planning.taskplanning.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("auth")
public class LoginController {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private LoginService loginService;


    @PostMapping
    public ResponseEntity<Token> autenticar(@RequestBody Login login){
        return ResponseEntity.ok(loginService.authenticate(login));
    }

    /*@RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public ResponseEntity<Void> forgot() {
        return ResponseEntity.noContent().build();
    }*/
}
