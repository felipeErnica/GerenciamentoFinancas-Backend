package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.entity.DuplicataEntity;
import com.santacarolina.financeiro.entity.ProdutoEntity;

/**
 * ProdutoDuplicata
 */
public class ProdutoDuplicataDTO {

    private ProdutoDTO produto;
    private DuplicataDTO duplicata;

    public ProdutoDuplicataDTO(ProdutoEntity produto, DuplicataEntity duplicata) {
        this.produto = new ProdutoDTO(produto);
        this.duplicata = new DuplicataDTO(duplicata);
    }

    public ProdutoDTO getProduto() { return produto; }
    public DuplicataDTO getDuplicata() { return duplicata; }

}
