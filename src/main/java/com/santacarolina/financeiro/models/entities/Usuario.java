package com.santacarolina.financeiro.models.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String nomeUsuario;

    public long getId() {return id;}
    public String getNomeUsuario() {return nomeUsuario;}

}
