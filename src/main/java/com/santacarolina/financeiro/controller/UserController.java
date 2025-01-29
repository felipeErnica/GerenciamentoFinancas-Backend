package com.santacarolina.financeiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santacarolina.financeiro.entity.UserEntity;
import com.santacarolina.financeiro.service.UserService;

@Controller
@RequestMapping("/user")
@SuppressWarnings("rawtypes")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/login")
    public ResponseEntity authenticate(@RequestBody UserEntity user) {
        try {
            service.loadUserByUsername(user.getUsername());
            return ResponseEntity.ok().build();
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity register (@RequestBody UserEntity user) {
        service.register(user);
        return ResponseEntity.ok().build();
    }
    
}
