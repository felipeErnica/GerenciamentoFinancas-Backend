package com.santacarolina.financeiro.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @ManyToOne
    @JoinColumn(name = "documento_id")
    private DocumentoFiscal documento;
    private long classificacaoFiscal;
    private String descricao;
    private String und;
    private double quantidade;
    private double valorUnit;
    @Transient
    private double valorTotal;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    public long getId() {return id;}
    public DocumentoFiscal getDocumento() {return documento;}
    public long getClassificacaoFiscal() {return classificacaoFiscal;}
    public String getDescricao() {return descricao;}
    public String getUnd() {return und;}
    public double getQuantidade() {return quantidade;}
    public double getValorUnit() {return valorUnit;}
    public double getValorTotal() {return valorUnit*quantidade;}

}
