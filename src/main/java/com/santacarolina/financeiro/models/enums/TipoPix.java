package com.santacarolina.financeiro.models.enums;

public enum TipoPix {

    CPF(0),
    CNPJ(1),
    EMAIL(2),
    TELEFONE(3),
    CHAVE_ALEATORIA(4);

    private int tipoPix;

    TipoPix(int tipoPix) {
        this.tipoPix = tipoPix;
    }

    public int getTipoPix() {
        return tipoPix;
    }
}
