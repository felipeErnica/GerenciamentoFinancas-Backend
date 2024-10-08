package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.enums.TipoPix;
import com.santacarolina.financeiro.interfaces.DataDAO;

public class DadoDTO implements DataDAO {

    private long id;
    private String agencia;
    private long bancoId;
    private String numeroConta;
    private long contatoId;
    private Long pixId;
    private PixDTO pixDTO;
    private String chave;
    private TipoPix tipoPix;
    private String nomeContato;
    private String nomeBanco;

    public DadoDTO(long id, String agencia, long bancoId, String numeroConta, long contatoId, Long pixId,
                   String chave, TipoPix tipoPix, String nomeContato, String nomeBanco) {
        this.id = id;
        this.agencia = agencia;
        this.bancoId = bancoId;
        this.numeroConta = numeroConta;
        this.contatoId = contatoId;
        this.pixId = pixId;
        this.chave = chave;
        this.tipoPix = tipoPix;
        this.nomeContato = nomeContato;
        this.nomeBanco = nomeBanco;
    }

    @Override
    public long getId() { return id; }
    public String getAgencia() { return agencia; }
    public long getBancoId() { return bancoId; }
    public String getNumeroConta() { return numeroConta; }
    public long getContatoId() { return contatoId; }
    public Long getPixId() { return pixId; }
    public String getChave() { return chave; }
    public TipoPix getTipoPix() { return tipoPix; }
    public String getNomeContato() { return nomeContato; }
    public String getNomeBanco() { return nomeBanco; }

    public PixDTO getPixDTO() { return pixDTO; }

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
        sb.append(", pixId=").append(pixId);
        sb.append('}');
        return sb.toString();
    }
}
