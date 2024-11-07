package com.santacarolina.financeiro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "dados_bancarios")
public class DadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "pix_id")
    private PixEntity chavePix;

    @ManyToOne
    @JoinColumn(name = "banco_id")
    private BancoEntity banco;

    private String agencia;
    private String numeroConta;

    public long getId() { return id; }
    public BancoEntity getBanco() { return banco; }
    public String getAgencia() { return agencia; }
    public String getNumeroConta() { return numeroConta; }

}

