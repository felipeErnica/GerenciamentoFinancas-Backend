package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.entity.ExtratoEntity;
import com.santacarolina.financeiro.interfaces.DataDAO;

import java.time.LocalDate;

public class ExtratoDTO implements DataDAO {

    private long id;
    private Long contaId;
    private LocalDate dataTransacao;
    private String categoriaExtrato;
    private String descricao;
    private double valor;
    private boolean isConciliado;

    public ExtratoDTO(ExtratoEntity entity) {
        this.id = entity.getId();
        this.contaId = entity.getConta() != null ? entity.getConta().getId() : 0;
        this.dataTransacao = entity.getDataTransacao();
        this.categoriaExtrato = entity.getCategoriaExtrato();
        this.descricao = entity.getDescricao();
        this.valor = entity.getValor();
        this.isConciliado = entity.isConciliado();
    }

    @Override
    public long getId() { return id; }
    public Long getContaId() { return contaId; }
    public LocalDate getDataTransacao() { return dataTransacao; }
    public String getCategoriaExtrato() { return categoriaExtrato; }
    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
    public boolean isConciliado() { return isConciliado; }

    @Override
    public void setId(long id) { this.id = id; }

}
