package com.santacarolina.financeiro.enums;

public enum TipoDocumento {

    NOTA_FISCAL("NOTA_FISCAL"),
    RECIBO("RECIBO"),
    DARF("DARF"),
    GARE("GARE"),
    HOLERITE("HOLERITE"),
    DARE("DARE"),
    NFE("NFE"),
    BOLETO("BOLETO"),
    CONTRATO("CONTRATO"),
    TRANSFERENCIA("TRANSFERENCIA"),
    IPTU("IPTU"),
    GRU("GRU"),
    CUPOM_FISCAL("CUPOM_FISCAL"),
    GRF("GRF");

    private String tipoDocumento;

    TipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }
    public String getValue() { return tipoDocumento; }

    public static TipoDocumento fromValues(String value) {
        for (TipoDocumento d : TipoDocumento.values()) {
            if (d.getValue().equals(value)) return d;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

}
