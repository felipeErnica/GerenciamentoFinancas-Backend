package com.santacarolina.financeiro.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santacarolina.financeiro.entity.User;
import com.santacarolina.financeiro.repository.UserRepository;

@Controller
@RequestMapping("/user")
@SuppressWarnings("rawtypes")
public class UserController {

    @Autowired
    private UserRepository repository;
    private static final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    
    @PostMapping("/register")
    public ResponseEntity save(@RequestBody User user) {
        String encondedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encondedPassword);

        repository.save(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/auth")
    public ResponseEntity authenticate(@RequestBody User user) {
        Optional<User> optional = repository.findByUsername(user.getUsername());
        if (optional.isPresent()) {
            if (passwordEncoder.matches(user.getPassword(), optional.get().getPassword())) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
