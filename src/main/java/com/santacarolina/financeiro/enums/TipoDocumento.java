package com.santacarolina.financeiro.enums;

public enum TipoDocumento {

    NOTA_FISCAL(0),
    RECIBO(1),
    DARF(2),
    GARE(3),
    HOLERITE(4),
    DARE(5),
    NFE(6),
    BOLETO(7),
    CONTRATO(8),
    TRANSFERENCIA(9),
    IPTU(10),
    GRU(11),
    CUPOM_FISCAL(12),
    GRF(13);

    private int tipoDocumento;

    TipoDocumento(int tipoDocumento) { this.tipoDocumento = tipoDocumento; }
    public int getValue() { return tipoDocumento; }

    public static TipoDocumento fromValues(int value) {
        for (TipoDocumento d : TipoDocumento.values()) {
            if (d.getValue() == value) return d;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

}
