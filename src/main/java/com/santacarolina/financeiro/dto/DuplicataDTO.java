package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.enums.FluxoCaixa;
import com.santacarolina.financeiro.enums.TipoPagamento;
import com.santacarolina.financeiro.interfaces.DataDAO;

import java.time.LocalDate;

public class DuplicataDTO implements DataDAO {

    private Long docId;
    private Long dadoId;
    private Long pixId;
    private long id;
    private int numDup;
    private TipoPagamento tipoPagamento;
    private LocalDate  dataVencimento;
    private String nomeContato;
    private String boletoCaminho;
    private double valor;
    private boolean paga;
    private String conta;
    private FluxoCaixa fluxoCaixa;

    public DuplicataDTO(Long docId, Long dadoId, long id, int numDup, TipoPagamento tipoPagamento,
                        LocalDate dataVencimento, String nomeContato, String boletoCaminho,
                        double valor, boolean paga, String conta, Long pixId, FluxoCaixa fluxoCaixa) {
        this.docId = docId;
        this.dadoId = dadoId;
        this.id = id;
        this.numDup = numDup;
        this.tipoPagamento = tipoPagamento;
        this.dataVencimento = dataVencimento;
        this.nomeContato = nomeContato;
        this.boletoCaminho = boletoCaminho;
        this.valor = valor;
        this.paga = paga;
        this.conta = conta;
        this.pixId = pixId;
        this.fluxoCaixa = fluxoCaixa;
    }

    @Override
    public long getId() { return id; }
    public Long getDocId() { return docId; }
    public Long getDadoId() { return dadoId; }
    public int getNumDup() { return numDup; }
    public TipoPagamento getTipoPagamento() { return tipoPagamento; }
    public LocalDate getDataVencimento() { return dataVencimento; }
    public String getNomeContato() { return nomeContato; }
    public String getBoletoCaminho() { return boletoCaminho; }
    public double getValor() { return valor; }
    public boolean isPaga() { return paga; }
    public String getConta() { return conta; }
    public Long getPixId() { return pixId; }
    public FluxoCaixa getFluxoCaixa() { return fluxoCaixa; }

    @Override
    public void setId(long id) { this.id = id; }
    public void setDocId(Long docId) { this.docId = docId; }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DuplicataDTO{");
        sb.append("docId=").append(docId);
        sb.append(", dadoId=").append(dadoId);
        sb.append(", pixId=").append(pixId);
        sb.append(", id=").append(id);
        sb.append(", numDup=").append(numDup);
        sb.append(", tipoPagamento=").append(tipoPagamento);
        sb.append(", dataVencimento='").append(dataVencimento).append('\'');
        sb.append(", nomeContato='").append(nomeContato).append('\'');
        sb.append(", boletoCaminho='").append(boletoCaminho).append('\'');
        sb.append(", valor=").append(valor);
        sb.append(", paga=").append(paga);
        sb.append(", conta='").append(conta).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
