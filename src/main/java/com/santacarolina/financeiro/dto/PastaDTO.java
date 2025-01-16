package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.entity.PastaEntity;

public class PastaDTO {

    private long id;
    private String nome;
    private String caminhoPasta;
    private long contaId;

    public PastaDTO(PastaEntity entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.caminhoPasta = entity.getCaminhoPasta();
        this.contaId = entity.getConta().getId();
    }

    public long getId() { return id; }
    public String getNome() { return nome; }
    public String getCaminhoPasta() { return caminhoPasta; }
    public long getContaId() { return contaId; }

}
