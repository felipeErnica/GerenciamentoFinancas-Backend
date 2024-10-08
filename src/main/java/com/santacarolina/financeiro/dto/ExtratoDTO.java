package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.interfaces.DataDAO;

import java.time.LocalDate;

public class ExtratoDTO implements DataDAO {

    private long id;
    private Long contaId;
    private LocalDate dataTransacao;
    private String contaBancaria;
    private String categoriaExtrato;
    private String descricao;
    private double valor;
    private boolean conciliado;

    public ExtratoDTO(long id, Long contaId, LocalDate dataTransacao, String contaBancaria, String categoriaExtrato,
                      String descricao, double valor, boolean isConciliado) {
        this.id = id;
        this.contaId = contaId;
        this.dataTransacao = dataTransacao;
        this.contaBancaria = contaBancaria;
        this.categoriaExtrato = categoriaExtrato;
        this.descricao = descricao;
        this.valor = valor;
        this.conciliado = isConciliado;
    }

    @Override
    public long getId() { return id; }
    public Long getContaId() { return contaId; }
    public LocalDate getDataTransacao() { return dataTransacao; }
    public String getContaBancaria() { return contaBancaria; }
    public String getCategoriaExtrato() { return categoriaExtrato; }
    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
    public boolean isConciliado() { return conciliado; }

    @Override
    public void setId(long id) { this.id = id; }

}
