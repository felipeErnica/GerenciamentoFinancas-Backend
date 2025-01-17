package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.entity.ExtratoEntity;

import java.time.LocalDate;

public class ExtratoDTO {

    private long id;
    private ContaDTO conta;
    private LocalDate dataTransacao;
    private String categoriaExtrato;
    private String descricao;
    private double valor;
    private boolean conciliado;

    public ExtratoDTO(ExtratoEntity entity) {
        this.id = entity.getId();
        this.conta = entity.getConta() != null ? new ContaDTO(entity.getConta()) : null;
        this.dataTransacao = entity.getDataTransacao();
        this.categoriaExtrato = entity.getCategoriaExtrato();
        this.descricao = entity.getDescricao();
        this.valor = entity.getValor();
        this.conciliado = entity.isConciliado();
    }

    public long getId() { return id; }
    public ContaDTO getConta() { return conta; }
    public LocalDate getDataTransacao() { return dataTransacao; }
    public String getCategoriaExtrato() { return categoriaExtrato; }
    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
    public boolean isConciliado() { return conciliado; }

}
