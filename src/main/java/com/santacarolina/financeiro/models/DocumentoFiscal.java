package com.santacarolina.financeiro.models;

import com.santacarolina.financeiro.models.enums.TipoDocumento;
import com.santacarolina.financeiro.models.enums.FluxoCaixa;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "documentos")
public class DocumentoFiscal{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String docNumero;
    @Enumerated
    private TipoDocumento docTipo;
    @ManyToOne
    @JoinColumn(name = "emissario_id")
    private Contato contato;
    private String caminhoDocumento;
    @ManyToOne
    @JoinColumn(name = "pasta_id")
    private PastaContabil pastaContabil;
    private double valor;
    private LocalDate dataEmissao;
    @Enumerated
    private FluxoCaixa fluxoCaixa;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    public long getId() {return id;}
    public String getDocNumero() {return docNumero;}
    public TipoDocumento getDocTipo() {return docTipo;}
    public Contato getContato() {return contato;}
    public String getCaminhoDocumento() {return caminhoDocumento;}
    public PastaContabil getPastaContabil() {return pastaContabil;}
    public double getValor() {return valor;}
    public LocalDate getDataEmissao() {return dataEmissao;}
    public FluxoCaixa getFluxoCaixa() {return fluxoCaixa;}

}
