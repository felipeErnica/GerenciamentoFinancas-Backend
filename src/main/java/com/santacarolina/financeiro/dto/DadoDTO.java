package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.entity.DadoEntity;

public class DadoDTO {

    private long id;
    private String agencia;
    private String numeroConta;
    private BancoDTO banco;
    private ContatoDTO contato;

    public DadoDTO(DadoEntity entity) {
        this.id = entity.getId();
        this.agencia = entity.getAgencia();
        this.numeroConta = entity.getNumeroConta();
        this.banco = entity.getBanco() != null ? new BancoDTO(entity.getBanco()) : null;
        this.contato = entity.getContato() != null ? new ContatoDTO(entity.getContato()) : null;
    }

    public long getId() { return id; }
    public String getAgencia() { return agencia; }
    public String getNumeroConta() { return numeroConta; }
    public BancoDTO getBanco() { return banco; }
    public ContatoDTO getContato() { return contato; }

}
