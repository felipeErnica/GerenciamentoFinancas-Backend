package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.entity.DadoEntity;

public class DadoDTO {

    private long id;
    private String agencia;
    private long bancoId;
    private String numeroConta;
    private long contatoId;

    public DadoDTO(DadoEntity entity) {
        this.id = entity.getId();
        this.agencia = entity.getAgencia();
        this.bancoId = entity.getBanco() != null ? entity.getBanco().getId() : 0;
        this.numeroConta = entity.getNumeroConta();
        this.contatoId = entity.getContato() != null ? entity.getContato().getId() : 0;
    }

    public long getId() { return id; }
    public String getAgencia() { return agencia; }
    public long getBancoId() { return bancoId; }
    public String getNumeroConta() { return numeroConta; }
    public long getContatoId() { return contatoId; }

}
