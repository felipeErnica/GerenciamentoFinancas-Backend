package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.entity.ProdutoEntity;

public class ProdutoDTO {

    private long id;
    private DocumentoDTO documento;
    private ClassificacaoDTO classificacao;
    private String descricao;
    private String und;
    private double quantidade;
    private double valorUnit;

    public ProdutoDTO(ProdutoEntity entity) {
        this.id = entity.getId();
        this.documento = entity.getDocumento() != null ? new DocumentoDTO(entity.getDocumento()) : null;
        this.classificacao = entity.getClassificacao() != null ? new ClassificacaoDTO(entity.getClassificacao()) : null;
        this.descricao = entity.getDescricao();
        this.und = entity.getUnd();
        this.quantidade = entity.getQuantidade();
        this.valorUnit = entity.getValorUnit();
    }

    public long getId() { return id; }
    public DocumentoDTO getDocumento() { return documento; }
    public ClassificacaoDTO getClassificacao() { return classificacao; }
    public String getDescricao() { return descricao; }
    public String getUnd() { return und; }
    public double getQuantidade() { return quantidade; }
    public double getValorUnit() { return valorUnit; }
    public void setQuantidade(double quantidade) { this.quantidade = quantidade; }

}
