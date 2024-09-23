package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.enums.FluxoCaixa;
import com.santacarolina.financeiro.enums.TipoDocumento;
import com.santacarolina.financeiro.interfaces.DataDAO;
import com.santacarolina.financeiro.models.Contato;
import com.santacarolina.financeiro.models.PastaContabil;

public class DocumentoDTO implements DataDAO {

    private long id;
    private String numDoc;
    private TipoDocumento tipoDoc;
    private Long emissorId;
    private String caminho;
    private Long pastaId;
    private double valor;
    private String dataEmissao;
    private FluxoCaixa fluxoCaixa;

    public long getId() { return id; }
    public String getNumDoc() { return numDoc; }
    public TipoDocumento getTipoDoc() { return tipoDoc; }
    public Long getEmissorId() { return emissorId; }
    public String getCaminho() { return caminho; }
    public Long getPastaId() { return pastaId; }
    public double getValor() { return valor; }
    public String getDataEmissao() { return dataEmissao; }
    public FluxoCaixa getFluxoCaixa() { return fluxoCaixa; }

}
