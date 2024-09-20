package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.enums.FluxoCaixa;
import com.santacarolina.financeiro.enums.TipoDocumento;
import com.santacarolina.financeiro.models.Contato;
import com.santacarolina.financeiro.models.PastaContabil;

public record DocumentoDTO(
        long id,
        String numDoc,
        TipoDocumento tipoDoc,
        Long emissorId,
        String caminho,
        Long pastaId,
        double valor,
        String dataEmissao,
        FluxoCaixa fluxoCaixa){
}
