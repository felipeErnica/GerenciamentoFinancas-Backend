package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.entity.PixEntity;
import com.santacarolina.financeiro.enums.TipoPix;

public class PixDTO {

    private long id;
    private ContatoDTO contato;
    private DadoDTO dado;
    private TipoPix tipoPix;
    private String chave;

    public PixDTO(PixEntity entity) {
        this.id = entity.getId();
        this.contato = entity.getContato() != null ? new ContatoDTO(entity.getContato()) : null;
        this.dado = entity.getDado() != null ? new DadoDTO(entity.getDado()) : null;
        this.tipoPix = entity.getTipoPix();
        this.chave = entity.getChave();
    }

    public long getId() { return id; }
    public ContatoDTO getContato() { return contato; }
    public DadoDTO getDado() { return dado; }
    public TipoPix getTipoPix() { return tipoPix; }
    public String getChave() { return chave; }

}
