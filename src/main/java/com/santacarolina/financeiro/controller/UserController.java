package com.santacarolina.financeiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santacarolina.financeiro.dto.LoginDTO;
import com.santacarolina.financeiro.entity.AuthToken;
import com.santacarolina.financeiro.entity.UserEntity;
import com.santacarolina.financeiro.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/login")
    public ResponseEntity<AuthToken> authenticate(@RequestBody LoginDTO login) {
        AuthToken token = service.authenticateUser(login);
        System.out.println((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (token.token() == null) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthToken> register (@RequestBody UserEntity user) {
        service.register(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
}
