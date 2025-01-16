package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.entity.ProdutoEntity;

public class ProdutoDTO {

    private long id;
    private long docId;
    private long classificacaoId;
    private String descricao;
    private String und;
    private double quantidade;
    private double valorUnit;

    public ProdutoDTO(ProdutoEntity entity) {
        this.id = entity.getId();
        this.docId = entity.getDocumento().getId();
        this.classificacaoId = entity.getClassificacao().getId();
        this.descricao = entity.getDescricao();
        this.und = entity.getUnd();
        this.quantidade = entity.getQuantidade();
        this.valorUnit = entity.getValorUnit();
    }

    public long getId() { return id; }
    public long getDocId() { return docId; }
    public long getClassificacaoId() { return classificacaoId; }
    public String getDescricao() { return descricao; }
    public String getUnd() { return und; }
    public double getQuantidade() { return quantidade; }
    public double getValorUnit() { return valorUnit; }

}
