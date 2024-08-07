package com.santacarolina.financeiro.models;

import jakarta.persistence.*;

import java.util.List;

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
    @OneToMany(mappedBy = "contaBancaria")
    private List<PastaContabil> pastaList;
    @OneToMany(mappedBy = "contaBancaria")
    private List<Extrato> extratoList;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    public long getId() {return id;}
    public String getAgencia() {return agencia;}
    public Banco getBanco() {return banco;}
    public String getNumeroConta() {return numeroConta;}
    public List<PastaContabil> getPastaList() {return pastaList;}
    public List<Extrato> getExtratoList() {return extratoList;}

}
