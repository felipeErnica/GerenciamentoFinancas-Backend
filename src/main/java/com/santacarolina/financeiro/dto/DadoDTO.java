package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.interfaces.DataDAO;

public class DadoDTO implements DataDAO {

    private long id;
    private String agencia;
    private long bancoId;
    private String numeroConta;
    private long contatoId;
    private String nomeContato;
    private String nomeBanco;

    public DadoDTO(long id, String agencia, long bancoId, String numeroConta, long contatoId, String nomeContato, String nomeBanco) {
        this.id = id;
        this.agencia = agencia;
        this.bancoId = bancoId;
        this.numeroConta = numeroConta;
        this.contatoId = contatoId;
        this.nomeContato = nomeContato;
        this.nomeBanco = nomeBanco;
    }

    @Override
    public long getId() { return id; }
    public String getAgencia() { return agencia; }
    public long getBancoId() { return bancoId; }
    public String getNumeroConta() { return numeroConta; }
    public long getContatoId() { return contatoId; }
    public String getNomeContato() { return nomeContato; }
    public String getNomeBanco() { return nomeBanco; }

    @Override
    public void setId(long id) { this.id = id; }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DadoDTO{");
        sb.append("id=").append(id);
        sb.append(", agencia='").append(agencia).append('\'');
        sb.append(", bancoId=").append(bancoId);
        sb.append(", numeroConta='").append(numeroConta).append('\'');
        sb.append(", contatoId=").append(contatoId);
        sb.append('}');
        return sb.toString();
    }
}
