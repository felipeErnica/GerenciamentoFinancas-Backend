package com.santacarolina.financeiro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String username;
    private String password;

    public long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    
    public void setPassword(String password) { this.password = password; }

}
