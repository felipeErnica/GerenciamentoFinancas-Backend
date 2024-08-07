package com.santacarolina.financeiro.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "bancos")
public class Banco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String nomeBanco;
    @OneToMany(mappedBy = "banco")
    private List<ContaBancaria> contaBancariaList;
    @OneToMany(mappedBy = "banco")
    private List<DadoBancario> dadoBancarioList;

    public long getId() {return id;}
    public String getNomeBanco() {return nomeBanco;}

}
