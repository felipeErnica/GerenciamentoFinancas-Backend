package com.santacarolina.financeiro.enums;

public enum TipoPix {

    CPF(0),
    CNPJ(1),
    EMAIL(2),
    TELEFONE(3),
    CHAVE_ALEATORIA(4);

    private int tipoPix;

    TipoPix(int tipoPix) { this.tipoPix = tipoPix; }
    public int getValue() { return tipoPix; }

    public static TipoPix fromValue (int value) {
        for (TipoPix t : TipoPix.values()) {
            if (t.getValue() == value) return t;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

}
