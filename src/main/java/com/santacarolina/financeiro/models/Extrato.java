package com.santacarolina.financeiro.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private boolean isConciliado;

    public long getId() {return id;}
    public LocalDate getDataTransacao() {return dataTransacao;}
    public ContaBancaria getContaPasta() {return contaBancaria;}
    public String getCategoriaExtrato() {return categoriaExtrato;}
    public String getDescricao() {return descricao;}
    public double getValor() {return valor;}
    public boolean isConciliado() {return isConciliado;}

    public void setId(long id) { this.id = id; }
    public void setDataTransacao(LocalDate dataTransacao) { this.dataTransacao = dataTransacao; }
    public void setContaBancaria(ContaBancaria contaBancaria) { this.contaBancaria = contaBancaria; }
    public void setCategoriaExtrato(String categoriaExtrato) { this.categoriaExtrato = categoriaExtrato; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setValor(double valor) { this.valor = valor; }
    public void setConciliado(boolean conciliado) { isConciliado = conciliado; }

}
