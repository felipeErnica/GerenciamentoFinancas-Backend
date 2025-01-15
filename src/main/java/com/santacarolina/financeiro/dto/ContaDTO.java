package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.entity.ContaEntity;

public class ContaDTO {

    private long id;
    private String nomeConta;
    private String agencia;
    private long bancoId;
    private String numeroConta;
    private String abreviacaoConta;

    public ContaDTO(ContaEntity entity) {
        this.id = entity.getId();
        this.nomeConta = entity.getNomeConta();
        this.agencia = entity.getAgencia();
        this.bancoId = entity.getBanco() != null ? entity.getBanco().getId() : 0;
        this.numeroConta = entity.getNumeroConta();
        this.abreviacaoConta = entity.getAbreviacaoConta();
    }

    public long getId() { return id; }
    public String getNomeConta() { return nomeConta; }
    public String getAgencia() { return agencia; }
    public long getBancoId() { return bancoId; }
    public String getNumeroConta() { return numeroConta; }
    public String getAbreviacaoConta() { return abreviacaoConta; }

}
