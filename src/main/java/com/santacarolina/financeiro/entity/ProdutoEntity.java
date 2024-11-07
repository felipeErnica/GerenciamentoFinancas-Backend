package com.santacarolina.financeiro.entity;

import jakarta.persistence.Entity;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "documento_id")
    private DocumentoEntity documento;

    @ManyToOne
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

}

