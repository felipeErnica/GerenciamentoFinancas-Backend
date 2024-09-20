package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.models.Contato;
import com.santacarolina.financeiro.enums.TipoPix;

public record PixDTO(
    long id,
    Contato contato,
    Long dadoId,
    TipoPix tipoPix,
    String chave
){
}
