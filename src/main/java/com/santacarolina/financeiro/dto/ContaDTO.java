package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.interfaces.DataDAO;

public class ContaDTO implements DataDAO {

    private long id;
    private String nomeConta;
    private String agencia;
    private long bancoId;
    private String numeroConta;
    private String abreviacaoConta;

    @Override
    public long getId() { return id; }
    public String getNomeConta() { return nomeConta; }
    public String getAgencia() { return agencia; }
    public long getBancoId() { return bancoId; }
    public String getNumeroConta() { return numeroConta; }
    public String getAbreviacaoConta() { return abreviacaoConta; }

    public void setId(long id) { this.id = id; }
    public void setNomeConta(String nomeConta) { this.nomeConta = nomeConta; }
    public void setAgencia(String agencia) { this.agencia = agencia; }
    public void setBancoId(long bancoId) { this.bancoId = bancoId; }
    public void setNumeroConta(String numeroConta) { this.numeroConta = numeroConta; }
    public void setAbreviacaoConta(String abreviacaoConta) { this.abreviacaoConta = abreviacaoConta; }

}
