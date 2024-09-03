package com.santacarolina.financeiro.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "contas_bancarias")
public class ContaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String agencia;
    @ManyToOne
    @JoinColumn(name = "banco_id")
    private Banco banco;
    private String numeroConta;
    private String nomeConta;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    public long getId() { return id; }
    public String getAgencia() { return agencia; }
    public Banco getBanco() { return banco; }
    public String getNumeroConta() { return numeroConta; }
    public String getNomeConta() { return nomeConta; }

}
