package com.santacarolina.financeiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/username/{username}")
    public ResponseEntity<UserEntity> findByUsername(@PathVariable String username) {
        return service.findByUsername(username)
            .map(user -> ResponseEntity.ok(user))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/info")
    public ResponseEntity<UserEntity> getUser() { 
        return ResponseEntity.ok(UserService.getLoggedUser()); 
    }

    @PostMapping("/login")
    public ResponseEntity<AuthToken> authenticate(@RequestBody LoginDTO login) {
        AuthToken token = service.authenticateUser(login);
        if (token.token() == null) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthToken> register (@RequestBody UserEntity user) {
        service.register(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
}
