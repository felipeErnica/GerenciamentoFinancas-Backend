package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.entity.PastaEntity;

public class PastaDTO {

    private long id;
    private String nome;
    private String caminhoPasta;
    private ContaDTO conta;

    public PastaDTO(PastaEntity entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.caminhoPasta = entity.getCaminhoPasta();
        this.conta = entity.getConta() != null ? new ContaDTO(entity.getConta()) : null;
    }

    public long getId() { return id; }
    public String getNome() { return nome; }
    public String getCaminhoPasta() { return caminhoPasta; }
    public ContaDTO getConta() { return conta; }

}
