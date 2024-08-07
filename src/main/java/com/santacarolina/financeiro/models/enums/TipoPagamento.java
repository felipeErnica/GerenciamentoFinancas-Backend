package com.santacarolina.financeiro.models.enums;

public enum TipoPagamento {

    CARTAO_DEBITO(1),
    PIX(2),
    TRANSFERENCIA(3),
    DEBITO_CONTA(4),
    DINHEIRO_VIVO(5),
    BOLETO(6);

    private int tipoPagamento;

    TipoPagamento(int tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public int getTipoPagamento() {
        return tipoPagamento;
    }

}
