package com.santacarolina.financeiro.enums;

public enum FluxoCaixa {
    DESPESA(0),
    RECEITA(1);

    private int fluxoCaixa;
    FluxoCaixa(int fluxoCaixa) { this.fluxoCaixa = fluxoCaixa; }
    public int getValue() { return fluxoCaixa; }

    public static FluxoCaixa fromValue(int value) {
        for (FluxoCaixa f : FluxoCaixa.values()) {
            if (f.getValue() == value) return f;
        }
        throw  new ArrayIndexOutOfBoundsException();
    }

}
