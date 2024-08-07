package com.santacarolina.financeiro.models.enums;

public enum TipoPix {

    CPF(1),
    CNPJ(2),
    EMAIL(3),
    TELEFONE(4),
    CHAVE_ALEATORIA(5);

    private int tipoPix;

    TipoPix(int tipoPix) {
        this.tipoPix = tipoPix;
    }

    public int getTipoPix() {
        return tipoPix;
    }
}
