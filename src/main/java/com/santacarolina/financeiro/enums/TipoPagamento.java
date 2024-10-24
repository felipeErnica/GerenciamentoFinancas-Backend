package com.santacarolina.financeiro.enums;

public enum TipoPagamento {

    CARTAO_DEBITO(0),
    PIX(1),
    TED(2),
    DEBITO_CONTA(3),
    DINHEIRO_VIVO(4),
    BOLETO(5),
    CARTAO_CREDITO(6);

    private int tipoPagamento;

    TipoPagamento(int tipoPagamento) { this.tipoPagamento = tipoPagamento; }
    public int getValue() { return tipoPagamento; }

    public static TipoPagamento fromValue(int value) {
        for (TipoPagamento tipoPagamento : TipoPagamento.values()) {
            if (tipoPagamento.getValue() == value) return tipoPagamento;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

}
