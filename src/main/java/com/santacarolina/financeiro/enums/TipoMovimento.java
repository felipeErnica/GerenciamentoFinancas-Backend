package com.santacarolina.financeiro.enums;

public enum TipoMovimento {

    COMUM("COMUM"),
    ESTORNO("ESTORNO"),
    TRANSFERENCIA("TRANSFERENCIA"),
    PAGAMENTO_INDEVIDO("PAGAMENTO_INDEVIDO"),
    APLICACAO("APLICACAO"),
    TARIFA_BANCARIA("TARIFA_BANCARIA"),
    SEGURO_BANCARIO("SEGURO_BANCARIO"),
    ACOES("ACOES"),
    EMPRESTIMO("EMPRESTIMO"),
    VALOR_DESC("VALOR_DESC");

    private final String tipoMovimento;

    TipoMovimento(String tipoMovimento) { this.tipoMovimento = tipoMovimento; }
    public String getValue() { return tipoMovimento; }

    public static TipoMovimento fromValue(String value) {
        for (TipoMovimento tipoMovimento : TipoMovimento.values()) {
            if (tipoMovimento.getValue().equals(value)) return tipoMovimento;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

}
