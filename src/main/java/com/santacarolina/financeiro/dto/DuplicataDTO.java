package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.enums.TipoPagamento;
import com.santacarolina.financeiro.interfaces.DataDAO;

public class DuplicataDTO implements DataDAO {

    private Long docId;
    private Long dadoId;
    private long id;
    private int numDup;
    private TipoPagamento tipoPagamento;
    private String dataVencimento;
    private String nomeContato;
    private String boletoCaminho;
    private double valor;
    private boolean paga;
    private String conta;

    public DuplicataDTO(Long docId, Long dadoId, long id, int numDup, TipoPagamento tipoPagamento,
                        String dataVencimento, String nomeContato, String boletoCaminho, double valor, boolean paga, String conta) {
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
    }

    @Override
    public long getId() { return id; }
    public Long getDocId() { return docId; }
    public Long getDadoId() { return dadoId; }
    public int getNumDup() { return numDup; }
    public TipoPagamento getTipoPagamento() { return tipoPagamento; }
    public String getDataVencimento() { return dataVencimento; }
    public String getNomeContato() { return nomeContato; }
    public String getBoletoCaminho() { return boletoCaminho; }
    public double getValor() { return valor; }
    public boolean isPaga() { return paga; }
    public String getConta() { return conta; }

}
