package com.santacarolina.financeiro.models.entities;

import com.santacarolina.financeiro.models.enums.TipoPagamento;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "duplicatas")
public class Duplicata {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @ManyToOne
    @JoinColumn(name = "documento_id")
    private DocumentoFiscal documento;
    private int numDup;
    private LocalDate dataVencimento;
    private TipoPagamento tipoPagamento;
    private double valor;
    private String boletoCaminho;
    @ManyToOne
    @JoinColumn(name = "dado_id")
    private DadoBancario dadoBancario;
    @OneToMany(mappedBy = "duplicata")
    private List<Conciliacao> conciliacaoList;
    @Transient
    private boolean isPayed;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    public long getId() {return id;}
    public DocumentoFiscal getDocumento() {return documento;}
    public int getNumDup() {return numDup;}
    public LocalDate getDataVencimento() {return dataVencimento;}
    public TipoPagamento getTipoPagamento() {return tipoPagamento;}
    public double getValor() {return valor;}
    public String getBoletoCaminho() {return boletoCaminho;}
    public DadoBancario getDadoBancario() {return dadoBancario;}
    public List<Conciliacao> getConciliacaoList() {return conciliacaoList;}
    public boolean isPayed() {return !conciliacaoList.isEmpty();}

}
