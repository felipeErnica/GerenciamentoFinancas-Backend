package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.enums.TipoPix;
import com.santacarolina.financeiro.interfaces.DataDAO;

public class PixDTO implements DataDAO {

    private long id;
    private long contatoId;
    private String nomeContato;
    private Long dadoId;
    private TipoPix tipoPix;
    private String chave;
    private String nomeBanco;
    private String agencia;
    private String numeroConta;

    public PixDTO(long id, long contatoId, String nomeContato, Long dadoId, TipoPix tipoPix, String chave,
                  String nomeBanco, String agencia, String numeroConta) {
        this.id = id;
        this.contatoId = contatoId;
        this.nomeContato = nomeContato;
        this.dadoId = dadoId;
        this.tipoPix = tipoPix;
        this.chave = chave;
        this.nomeBanco = nomeBanco;
        this.agencia = agencia;
        this.numeroConta = numeroConta;
    }

    @Override
    public long getId() { return id; }
    public long getContatoId() { return contatoId; }
    public Long getDadoId() { return dadoId; }
    public TipoPix getTipoPix() { return tipoPix; }
    public String getChave() { return chave; }
    public String getNomeBanco() { return nomeBanco; }
    public String getAgencia() { return agencia; }
    public String getNumeroConta() { return numeroConta; }
    public String getNomeContato() { return nomeContato; }

    @Override
    public void setId(long id) { this.id = id; }

}
