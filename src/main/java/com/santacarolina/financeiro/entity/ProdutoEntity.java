package com.santacarolina.financeiro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "documento_id")
    private DocumentoEntity documento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classificacao_id")
    private ClassificacaoEntity classificacao;

    private String descricao;
    private String und;
    private double quantidade;
    private double valorUnit;

    public long getId() { return id; }
    public DocumentoEntity getDocumento() { return documento; }
    public ClassificacaoEntity getClassificacao() { return classificacao; }
    public String getDescricao() { return descricao; }
    public String getUnd() { return und; }
    public double getQuantidade() { return quantidade; }
    public double getValorUnit() { return valorUnit; }
    public void setValorUnit(double valorUnit) { this.valorUnit = valorUnit; }

}

