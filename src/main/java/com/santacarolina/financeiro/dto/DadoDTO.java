package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.interfaces.DataDAO;

public class DadoDTO implements DataDAO {

    private long id;
    private String agencia;
    private long bancoId;
    private String numeroConta;
    private long contatoId;
    private Long pixId;

    public DadoDTO(long id, String agencia, long bancoId, String numeroConta, long contatoId, Long pixId) {
        this.id = id;
        this.agencia = agencia;
        this.bancoId = bancoId;
        this.numeroConta = numeroConta;
        this.contatoId = contatoId;
        this.pixId = pixId;
    }

    @Override
    public long getId() { return id; }
    public String getAgencia() { return agencia; }
    public long getBancoId() { return bancoId; }
    public String getNumeroConta() { return numeroConta; }
    public long getContatoId() { return contatoId; }
    public Long getPixId() { return pixId; }

}
