package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.interfaces.DataDAO;

public class PastaDTO implements DataDAO {

    private long id;
    private String nome;
    private String caminhoPasta;
    private long contaId;

    public PastaDTO(long id, String nome, String caminhoPasta, Long dadoId) {
        this.id = id;
        this.nome = nome;
        this.caminhoPasta = caminhoPasta;
        this.contaId = dadoId;
    }

    @Override
    public long getId() { return id; }
    public String getNome() { return nome; }
    public String getCaminhoPasta() { return caminhoPasta; }
    public long getContaId() { return contaId; }

}
