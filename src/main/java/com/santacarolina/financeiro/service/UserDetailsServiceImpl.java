package com.santacarolina.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.santacarolina.financeiro.entity.UserEntity;
import com.santacarolina.financeiro.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não existe!"));
        return userEntity;
    }
    
}
