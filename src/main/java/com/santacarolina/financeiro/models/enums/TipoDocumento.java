package com.santacarolina.financeiro.models.enums;

public enum TipoDocumento {

    NOTA_FISCAL(1),
    RECIBO(2),
    DARF(3),
    GARE(4),
    HOLERITE(5),
    DARE(6),
    NFE(7),
    BOLETO(8),
    CONTRATO(9),
    TRANSFERENCIA(10),
    IPTU(11),
    GRU(12),
    CUPOM_FISCAL(13),
    GRF(14);

    private int tipoDocumento;

    TipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

}
