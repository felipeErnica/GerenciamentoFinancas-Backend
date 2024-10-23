package com.santacarolina.financeiro.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.santacarolina.financeiro.dto.ProdutoDuplicataDTO;
import com.santacarolina.financeiro.enums.FluxoCaixa;
import com.santacarolina.financeiro.enums.TipoDocumento;
import com.santacarolina.financeiro.interfaces.DAO;
import com.santacarolina.financeiro.util.CommonDAO;
import com.santacarolina.financeiro.util.DataBaseConn;

/**
 * ProdutoDuplicataDAO
 */
@Component
public class ProdutoDuplicataDAO implements DAO<ProdutoDuplicataDTO> {

    private static final String SELECT_QUERY = """
        SELECT prod.descricao, prod.und, prod.quantidade, prod.valor_unit, prod.documento_id, prod.id as prod_id, prod.classificacao_id,
            doc.doc_tipo, doc.doc_numero, doc.pasta_id, doc.emissor_id,
            dup.id as dup_id, dup.data_vencimento,
            pasta.nome as nome_pasta,
            emissor.nome as nome_emissor,
            class.nome_classificacao, class.fluxo_caixa
        FROM produtos as prod
            LEFT JOIN documentos as doc ON doc.id = prod.documento_id
            LEFT JOIN duplicatas as dup ON dup.documento_id = prod.documento_id
            LEFT JOIN pastas_contabeis as pasta ON pasta.id = doc.pasta_id
            LEFT JOIN contatos as emissor ON emissor.id = doc.emissor_id
            LEFT JOIN classificacoes_contabeis as class ON class.id = prod.classificacao_id
        ORDER BY documento_id;
    """;

    private CommonDAO<ProdutoDuplicataDTO> commonDAO;

    @Autowired
    public ProdutoDuplicataDAO(DataBaseConn conn) {
        this.commonDAO = new CommonDAO<>(this, conn);
    }

    public List<ProdutoDuplicataDTO> findAll() throws SQLException { return commonDAO.findList(SELECT_QUERY); }

    @Override
    public ProdutoDuplicataDTO getDTO(ResultSet rs) throws SQLException {
        return new ProdutoDuplicataDTO(rs.getLong("prod_id"),
            rs.getString("descricao"),
            rs.getString("unid"),
            rs.getDouble("quantidade"),
            rs.getDouble("valor_unit"),
            rs.getLong("documento_id"),
            TipoDocumento.fromValues(rs.getInt("doc_tipo")),
            rs.getLong("doc_numero"),
            rs.getLong("dup_id"),
            rs.getDate("data_vencimento").toLocalDate(),
            rs.getLong("pasta_id"),
            rs.getString("nome_pasta"),
            rs.getLong("emissor_id"),
            rs.getString("nome_emissor"),
            FluxoCaixa.fromValue(rs.getInt("fluxo_caixa")),
            rs.getLong("classificacao_id"),
            rs.getString("nome_classificacao"));
    }

    @Override
    public void prepareValuesDTO(PreparedStatement ps, ProdutoDuplicataDTO t) throws SQLException { }

    @Override
    public int getIdParameterIndex() { return 0; }
    
}
