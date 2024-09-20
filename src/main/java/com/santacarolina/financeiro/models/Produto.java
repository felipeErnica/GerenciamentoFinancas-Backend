package com.santacarolina.financeiro.models;

public class Produto {

    private long id;
    private DocumentoFiscal documento;
    private ClassificacaoContabil classificacao;
    private String descricao;
    private String und;
    private double quantidade;
    private double valorUnit;

    public long getId() {return id;}
    public DocumentoFiscal getDocumento() {return documento;}
    public ClassificacaoContabil getClassificacao() {return classificacao;}
    public String getDescricao() {return descricao;}
    public String getUnd() {return und;}
    public double getQuantidade() {return quantidade;}
    public double getValorUnit() {return valorUnit;}
    public double getValorTotal() {return valorUnit*quantidade;}

}
