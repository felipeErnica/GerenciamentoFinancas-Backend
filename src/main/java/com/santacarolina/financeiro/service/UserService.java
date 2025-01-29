package com.santacarolina.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.santacarolina.financeiro.entity.AuthToken;
import com.santacarolina.financeiro.entity.UserEntity;
import com.santacarolina.financeiro.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public AuthToken authenticateUser(UserEntity user) {
        System.out.println("\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            user.getUsername(), 
            user.getPassword());
        System.out.println("\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n");
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        System.out.println("\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n");
        UserEntity authUser = (UserEntity) authentication.getPrincipal();
        System.out.println("\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n");
        return new AuthToken(jwtTokenService.generateToken(authUser));
    }

    public void register(UserEntity user) {
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repository.save(user);
    }

}
