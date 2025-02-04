package com.santacarolina.financeiro.enums;

public enum TipoPagamento {

    CARTAO_DEBITO("CARTÃO DE DÉBITO"),
    PIX("PIX"),
    TED("TED"),
    DEBITO_CONTA("DÉBITO EM CONTA"),
    DINHEIRO_VIVO("DINHEIRO VIVO"),
    BOLETO("BOLETO"),
    CARTAO_CREDITO("CARTÃO DE CRÉDITO");

    private String tipoPagamento;

    TipoPagamento(String tipoPagamento) { this.tipoPagamento = tipoPagamento; }
    public String getValue() { return tipoPagamento; }

    public static TipoPagamento fromValue(String value) {
        for (TipoPagamento tipoPagamento : TipoPagamento.values()) {
            if (tipoPagamento.getValue().equals(value)) return tipoPagamento;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

}
