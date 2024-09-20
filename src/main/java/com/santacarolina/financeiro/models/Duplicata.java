package com.santacarolina.financeiro.models;

import com.santacarolina.financeiro.enums.TipoPagamento;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public class Duplicata {

    private long id;
    private DocumentoFiscal doc;
    private Long docId;
    private Long dadoId;
    private int numDup;
    private TipoPagamento tipoPagamento;
    private String dataVencimento;
    private String boletoCaminho;
    private double valor;
    private boolean paga;

    public long getId() { return id; }
    public Long getDocId() { return docId; }
    public Long getDadoId() { return dadoId; }
    public int getNumDup() { return numDup; }
    public TipoPagamento getTipoPagamento() { return tipoPagamento; }
    public String getDataVencimento() { return dataVencimento; }
    public String getBoletoCaminho() { return boletoCaminho; }
    public double getValor() { return valor; }
    public boolean isPaga() { return paga; }

    public void setId(long id) { this.id = id; }
    public void setDocId(Long docId) { this.docId = docId; }
    public void setDadoId(Long dadoId) { this.dadoId = dadoId; }
    public void setNumDup(int numDup) { this.numDup = numDup; }
    public void setTipoPagamento(TipoPagamento tipoPagamento) { this.tipoPagamento = tipoPagamento; }
    public void setDataVencimento(String dataVencimento) { this.dataVencimento = dataVencimento; }
    public void setBoletoCaminho(String boletoCaminho) { this.boletoCaminho = boletoCaminho; }
    public void setValor(double valor) { this.valor = valor; }
    public void setPaga(boolean paga) { this.paga = paga; }

}
