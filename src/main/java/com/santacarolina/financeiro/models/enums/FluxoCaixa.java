package com.santacarolina.financeiro.models.enums;

public enum FluxoCaixa {

    DESPESA(1),
    RECEITA(2);

    private int fluxoCaixa;

    FluxoCaixa(int fluxoCaixa) {
        this.fluxoCaixa = fluxoCaixa;
    }

    public int getFluxoCaixa() {
        return fluxoCaixa;
    }

}
