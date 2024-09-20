package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.models.Banco;
import com.santacarolina.financeiro.models.Contato;

public record DadoDTO(
        long id,
        String agencia,
        Banco banco,
        String numeroConta,
        Contato contato,
        Long pixId) {
}
