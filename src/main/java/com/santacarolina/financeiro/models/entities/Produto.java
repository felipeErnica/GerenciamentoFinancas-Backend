package com.santacarolina.financeiro.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @ManyToOne
    @JoinColumn(name = "documento_id")
    private DocumentoFiscal documento;
    @ManyToOne
    @JoinColumn(name = "classificacao_id")
    private ClassificacaoContabil classificacao;
    private String descricao;
    private String und;
    private double quantidade;
    private double valorUnit;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    public long getId() {return id;}
    public DocumentoFiscal getDocumento() {return documento;}
    public ClassificacaoContabil getClassificacao() {return classificacao;}
    public String getDescricao() {return descricao;}
    public String getUnd() {return und;}
    public double getQuantidade() {return quantidade;}
    public double getValorUnit() {return valorUnit;}
    public double getValorTotal() {return valorUnit*quantidade;}

}
