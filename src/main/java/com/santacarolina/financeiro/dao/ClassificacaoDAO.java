package com.santacarolina.financeiro.dao;

import com.santacarolina.financeiro.dto.ClassificacaoDTO;
import com.santacarolina.financeiro.enums.FluxoCaixa;
import com.santacarolina.financeiro.interfaces.DAO;
import com.santacarolina.financeiro.util.CommonDAO;
import com.santacarolina.financeiro.util.DataBaseConn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class ClassificacaoDAO implements DAO<ClassificacaoDTO> {

    private static final String SELECT_QUERY = """
            SELECT class.id, class.categoria_id, class.numero_identificacao, class.nome_classificacao, class.fluxo_caixa,
                cat.nome as nome_categoria
            FROM classificacoes_contabeis class
                LEFT JOIN categorias_contabeis as cat ON cat.id = class.categoria_id
            """;
    private static final String UPDATE_QUERY = """
            UPDATE classificacaoes_contabeis
            SET categoria_id = ?, fluxo_caixa = ?, numero_identificacao = ?, nome_classificacao = ?
            WHERE id = ?;
            """;
    private static final String INSERT_QUERY = """
        INSERT INTO classificacoes_contabeis(categoria_id, fluxo_caixa, numero_identificacao, nome_classificacao) 
            VALUES(?,?,?,?)
        """;
    private static final String DELETE_QUERY = "DELETE FROM classificacoes_contabeis WHERE id = ?";

    private CommonDAO<ClassificacaoDTO> commonDAO;

    @Autowired
    public ClassificacaoDAO(DataBaseConn conn) { this.commonDAO = new CommonDAO<>(this, conn); }

    public List<ClassificacaoDTO> findAll() throws SQLException {
        return commonDAO.findList(SELECT_QUERY + "ORDER BY class.fluxo_caixa, class.categoria_id"); 
    }

    public Optional<ClassificacaoDTO> findByNome(String nome) throws SQLException {
        String query = SELECT_QUERY + " WHERE class.nome_classificacao = " + nome.replace("+", " ");
        return commonDAO.findOne(query);
    }

    public Optional<ClassificacaoDTO> getByNumero(long numeroIdentificacao) throws SQLException {
        String query = SELECT_QUERY + " WHERE class.numero_identificacao = " + numeroIdentificacao;
        return commonDAO.findOne(query);
    }

    public Optional<ClassificacaoDTO> findById(long id) throws SQLException {
        String query = SELECT_QUERY + " WHERE class.id = " + id;
        return commonDAO.findOne(query);
    }

    public List<ClassificacaoDTO> findByCategoria(long categoriaId) throws SQLException {
        String query = SELECT_QUERY + " WHERE class.categoria_id = " + categoriaId;
        return commonDAO.findList(query);
    }

    public void save(ClassificacaoDTO dto) throws SQLException { commonDAO.save(dto, UPDATE_QUERY, INSERT_QUERY); }

    public void deleteBatch(List<ClassificacaoDTO> list) throws SQLException {
        commonDAO.deleteBatch(DELETE_QUERY, list);
    }

    @Override
    public ClassificacaoDTO getDTO(ResultSet rs) throws SQLException {
        return new ClassificacaoDTO(
                rs.getLong("id"),
                rs.getLong("categoria_id"),
                rs.getString("nome_categoria"),
                FluxoCaixa.fromValue(rs.getInt("fluxo_caixa")),
                rs.getLong("numero_identificacao"),
                rs.getString("nome_classificacao"));
    }

    @Override
    public void prepareValuesDTO(PreparedStatement ps, ClassificacaoDTO c) throws SQLException {
        ps.setLong(1, c.getCategoriaId());
        ps.setInt(2, c.getFluxoCaixa().getValue());
        ps.setLong(3, c.getNumeroIdentificacao());
        ps.setString(4, c.getNomeClassificacao());
    }

    @Override
    public int getIdParameterIndex() { return 4; }

}
