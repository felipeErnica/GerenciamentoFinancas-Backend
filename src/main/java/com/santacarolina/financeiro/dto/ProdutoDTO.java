package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.models.ClassificacaoContabil;
import com.santacarolina.financeiro.models.DocumentoFiscal;
import jakarta.persistence.*;

import java.time.LocalDate;

public record ProdutoDTO(
        long id,
        long docId,
        long classificacaoId,
        String descricao,
        String und,
        double quantidade,
        double valorUnit,
        LocalDate dataEmissao,
        String nomePasta,
        String nomeContato,
        String classificacao){
}
