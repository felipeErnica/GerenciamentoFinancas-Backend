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
            SELECT id, numero_identificacao, nome_classificacao
            FROM classificacoes_contabeis
            """;
    private static final String UPDATE_QUERY = """
            UPDATE classificacaoes_contabeis
            SET fluxo_caixa = ?, numero_identificacao = ?, nome_classificacao = ?
            WHERE id = ?;
            """;
    private static final String INSERT_QUERY = """
        INSERT INTO classificacoes_contabeis(fluxo_caixa, numero_identificacao, nome_classificacao) 
            VALUES(?,?,?)
        """;
    private static final String DELETE_QUERY = "DELETE FROM classificacoes_contabeis WHERE id = ?";

    private CommonDAO<ClassificacaoDTO> commonDAO;

    @Autowired
    public ClassificacaoDAO(DataBaseConn conn) { this.commonDAO = new CommonDAO<>(this, conn); }

    public List<ClassificacaoDTO> findAll() throws SQLException { return commonDAO.findList(SELECT_QUERY); }

    public Optional<ClassificacaoDTO> getByNumero(long numeroIdentificacao) throws SQLException {
        String query = SELECT_QUERY + "WHERE numero_identificacao = " + numeroIdentificacao;
        return commonDAO.findOne(query);
    }

    @Override
    public ClassificacaoDTO getDTO(ResultSet rs) throws SQLException {
        return new ClassificacaoDTO(
                rs.getLong("id"),
                FluxoCaixa.fromValue(rs.getInt("fluxo_caixa")),
                rs.getLong("numero_identificacao"),
                rs.getString("nome_classificacao"));
    }

    @Override
    public void prepareValuesDTO(PreparedStatement ps, ClassificacaoDTO c) throws SQLException {
        ps.setInt(1, c.getFluxoCaixa().getValue());
        ps.setLong(2, c.getNumeroIdentificacao());
        ps.setString(3, c.getNomeClassificacao());
    }

    @Override
    public int getIdParameterIndex() { return 4; }

}
