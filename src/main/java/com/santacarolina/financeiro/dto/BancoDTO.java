package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.interfaces.DataDAO;

public class BancoDTO implements DataDAO {

    private long id;
    private String nomeBanco;
    private String apelidoBanco;

    public BancoDTO(long id, String nomeBanco, String apelidoBanco) {
        this.id = id;
        this.nomeBanco = nomeBanco;
        this.apelidoBanco = apelidoBanco;
    }

    @Override
    public long getId() { return id; }
    public String getNomeBanco() { return nomeBanco; }
    public String getApelidoBanco() { return apelidoBanco; }

}
