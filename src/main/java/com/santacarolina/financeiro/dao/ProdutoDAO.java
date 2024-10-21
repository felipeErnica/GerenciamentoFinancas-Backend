package com.santacarolina.financeiro.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.santacarolina.financeiro.dto.ProdutoDTO;
import com.santacarolina.financeiro.interfaces.DAO;
import com.santacarolina.financeiro.util.CommonDAO;
import com.santacarolina.financeiro.util.DataBaseConn;

@Component
public class ProdutoDAO implements DAO<ProdutoDTO> {

    private static  final String SELECT_QUERY = """
                SELECT p.id, p.documento_id, p.classificacao_id, p.descricao, p.und, p.quantidade, p.valor_unit,
                    d.data_emissao,
                    c.nome as nome_contato,
                    pc.nome as nome_pasta,
                    cc.nome_classificacao
                FROM produtos p
                    LEFT JOIN documentos d ON d.id = p.documento_id
                    LEFT JOIN contatos c ON c.id = d.emissor_id
                    LEFT JOIN pastas_contabeis pc ON pc.id = d.pasta_id
                    LEFT JOIN classificacoes_contabeis cc ON cc.id = p.classificacao_id
            """;
    private static final String UPDATE_QUERY = """
            UPDATE public.produtos
                SET documento_id=?, classificacao_id=?, descricao=?, und=?, quantidade=?, valor_unit=?
                WHERE id = ?;
            """;
    private static final String INSERT_QUERY = """
            INSERT INTO public.produtos(
                documento_id, classificacao_id, descricao, und, quantidade, valor_unit)
                VALUES (?, ?, ?, ?, ?, ?);
            """;
    private static final String DELETE_QUERY = "DELETE FROM produtos WHERE id = ?;";

    private CommonDAO<ProdutoDTO> commonDAO;

    @Autowired
    public ProdutoDAO(DataBaseConn conn) {
        this.commonDAO = new CommonDAO<>(this, conn);
    }

    public List<ProdutoDTO> findAll() throws SQLException {
        String query = SELECT_QUERY  + "ORDER BY d.data_emissao DESC;";
        return commonDAO.findList(query);
    }

    public List<ProdutoDTO> findByDoc(long documentoId) throws SQLException {
        String query = SELECT_QUERY + "WHERE documento_id = " + documentoId;
        return commonDAO.findList(query);
    }

    public void saveAll(List<ProdutoDTO> produtoList) throws SQLException { commonDAO.saveBatch(produtoList, UPDATE_QUERY, INSERT_QUERY); }

    @Override
    public ProdutoDTO getDTO(ResultSet rs) throws SQLException {
        return new ProdutoDTO(
                rs.getLong("id"),
                rs.getLong("documento_id"),
                rs.getLong("classificacao_id"),
                rs.getString("descricao"),
                rs.getString("und"),
                rs.getDouble("quantidade"),
                rs.getDouble("valor_unit"),
                rs.getDate("data_emissao").toLocalDate(),
                rs.getString("nome_pasta"),
                rs.getString("nome_contato"),
                rs.getString("nome_classificacao")
        );
    }

    @Override
    public void prepareValuesDTO(PreparedStatement ps, ProdutoDTO p) throws SQLException {
        ps.setLong(1, p.getDocId());
        ps.setLong(2, p.getClassificacaoId());
        ps.setString(3, p.getDescricao());
        ps.setString(4, p.getUnd());
        ps.setDouble(5, p.getQuantidade());
        ps.setDouble(6, p.getValorUnit());
    }

    @Override
    public int getIdParameterIndex() { return 7; }

}
