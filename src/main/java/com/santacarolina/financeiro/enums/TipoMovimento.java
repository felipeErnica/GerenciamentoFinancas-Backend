package com.santacarolina.financeiro.enums;

public enum TipoMovimento {

    COMUM(1),
    ESTORNO(2),
    TRANSFERENCIA(3),
    PAGAMENTO_INDEVIDO(4),
    APLICACAO(5),
    TARIFA_BANCARIA(6),
    SEGURO_BANCARIO(7),
    ACOES(8),
    EMPRESTIMO(9),
    VALOR_DESC(10);

    private final int tipoMovimento;
    TipoMovimento(int tipoMovimento) { this.tipoMovimento = tipoMovimento; }
    public int getValue() { return tipoMovimento; }
    public static TipoMovimento fromValue(int value) {
        for (TipoMovimento tipoMovimento : TipoMovimento.values()) {
            if (tipoMovimento.getValue() == value) return tipoMovimento;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

}
