package com.santacarolina.financeiro.enums;

public enum FluxoCaixa {
    DESPESA("DESPESA"),
    RECEITA("RECEITA");

    private String fluxoCaixa;
    FluxoCaixa(String fluxoCaixa) { this.fluxoCaixa = fluxoCaixa; }
    public String getValue() { return fluxoCaixa; }

    public static FluxoCaixa fromValue(String value) {
        for (FluxoCaixa f : FluxoCaixa.values()) {
            if (f.getValue().equals(value)) return f;
        }
        throw  new ArrayIndexOutOfBoundsException();
    }

}
