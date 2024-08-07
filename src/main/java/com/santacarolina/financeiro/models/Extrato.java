package com.santacarolina.financeiro.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "extratos")
public class Extrato {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private LocalDate dataTransacao;
    @ManyToOne
    @JoinColumn(name = "conta_id")
    private ContaBancaria contaBancaria;
    private String categoriaExtrato;
    private String descricao;
    private double valor;
    @OneToMany(mappedBy = "extrato")
    private List<Conciliacao> conciliacaoList;
    @Transient
    private boolean isConciliacao;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    public long getId() {return id;}
    public LocalDate getDataTransacao() {return dataTransacao;}
    public ContaBancaria getContaPasta() {return contaBancaria;}
    public String getCategoriaExtrato() {return categoriaExtrato;}
    public String getDescricao() {return descricao;}
    public double getValor() {return valor;}
    public List<Conciliacao> getConciliacaoList() {return conciliacaoList;}
    public boolean isConciliacao() {return !conciliacaoList.isEmpty();}

}
