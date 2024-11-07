package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.enums.FluxoCaixa;
import com.santacarolina.financeiro.enums.TipoDocumento;
import com.santacarolina.financeiro.interfaces.DataDAO;

import java.time.LocalDate;
import java.util.List;

public class DocumentoDTO implements DataDAO {

    private long id;
    private Long numDoc;
    private TipoDocumento tipoDoc;
    private Long emissorId;
    private String caminho;
    private Long pastaId;
    private double valor;
    private LocalDate dataEmissao;
    private FluxoCaixa fluxoCaixa;
    private String nomePasta;
    private String nomeContato;
    private List<DuplicataDTO> duplicataList;
    private List<ProdutoDTO> produtoList;

    public DocumentoDTO(long id, Long numDoc, TipoDocumento tipoDoc,
            Long emissorId, String caminho, Long pastaId, double valor, LocalDate dataEmissao, FluxoCaixa fluxoCaixa,
            String nomePasta, String nomeContato) {
        this.id = id;
        this.numDoc = numDoc;
        this.tipoDoc = tipoDoc;
        this.emissorId = emissorId;
        this.caminho = caminho;
        this.pastaId = pastaId;
        this.valor = valor;
        this.dataEmissao = dataEmissao;
        this.fluxoCaixa = fluxoCaixa;
        this.nomeContato = nomeContato;
        this.nomePasta = nomePasta;
    }

    @Override
    public long getId() {
        return id;
    }

    public Long getNumDoc() {
        return numDoc;
    }

    public TipoDocumento getTipoDoc() {
        return tipoDoc;
    }

    public Long getEmissorId() {
        return emissorId;
    }

    public String getCaminho() {
        return caminho;
    }

    public long getPastaId() {
        return pastaId;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public FluxoCaixa getFluxoCaixa() {
        return fluxoCaixa;
    }

    public String getNomePasta() {
        return nomePasta;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public List<DuplicataDTO> getDuplicataList() {
        return duplicataList;
    }

    public List<ProdutoDTO> getProdutoList() {
        return produtoList;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DocumentoDTO{");
        sb.append("id=").append(id);
        sb.append(", numDoc=").append(numDoc);
        sb.append(", tipoDoc=").append(tipoDoc);
        sb.append(", emissorId=").append(emissorId);
        sb.append(", caminho='").append(caminho).append('\'');
        sb.append(", pastaId=").append(pastaId);
        sb.append(", valor=").append(valor);
        sb.append(", dataEmissao=").append(dataEmissao);
        sb.append(", fluxoCaixa=").append(fluxoCaixa);
        sb.append(", nomePasta='").append(nomePasta).append('\'');
        sb.append(", nomeContato='").append(nomeContato).append('\'');
        sb.append(", duplicataList=").append(duplicataList);
        sb.append(", produtoList=").append(produtoList);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

}
