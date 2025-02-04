package com.santacarolina.financeiro.enums;

public enum TipoPix {

    CPF("CPF"),
    CNPJ("CNPJ"),
    EMAIL("EMAIL"),
    CHAVE_ALEATORIA("CHAVE ALEATÓRIA");

    private String tipoPix;

    TipoPix(String tipoPix) { this.tipoPix = tipoPix; }
    public String getValue() { return tipoPix; }

    public static TipoPix fromValue (String value) {
        for (TipoPix t : TipoPix.values()) {
            if (t.getValue().equals(value)) return t;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

}
