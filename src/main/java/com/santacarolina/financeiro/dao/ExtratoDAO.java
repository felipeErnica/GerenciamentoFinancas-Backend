package com.santacarolina.financeiro.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.santacarolina.financeiro.dto.ExtratoDTO;
import com.santacarolina.financeiro.interfaces.DAO;
import com.santacarolina.financeiro.util.CommonDAO;
import com.santacarolina.financeiro.util.DataBaseConn;

@Component
public class ExtratoDAO implements DAO<ExtratoDTO> {

    private final static String INSERT_QUERY = """
             INSERT INTO public.extratos(
                    data_transacao, conta_id, categoria_extrato, descricao, valor, is_conciliado)
                    VALUES (?, ?, ?, ?, ?, ?);
            """;
    private final static String UPDATE_QUERY = """
                UPDATE extratos
                    SET data_transacao = ?, conta_id = ?, categoria_extrato = ?, descricao = ?, valor = ?, is_conciliado = ?
                    WHERE id = ?
            """;
    private final static String SELECT_QUERY = """
            SELECT e.id, e.data_transacao, e.categoria_extrato, e.descricao, e.valor, e.is_conciliado, e.conta_id,
                c.agencia, c.numero_conta,
                b.nome_banco, b.apelido_banco
            FROM extratos e 
                LEFT JOIN contas_bancarias c ON c.id = e.conta_id
                LEFT JOIN bancos b ON b.id = c.banco_id 
            """;
    private static final String DELETE_QUERY = "DELETE FROM extratos WHERE id = ?;";

    private CommonDAO<ExtratoDTO> commonDAO;

    @Autowired
    public ExtratoDAO(DataBaseConn conn) { this.commonDAO = new CommonDAO<>(this, conn); }

    public List<ExtratoDTO> findByContaId(long contaId) throws SQLException {
        String query = SELECT_QUERY  + "WHERE conta_id = " + contaId  +
                " ORDER BY data_transacao DESC";
        return commonDAO.findList(query);
    }

    public List<ExtratoDTO> findByConciliacao(boolean isConciliado) throws SQLException {
        String query = SELECT_QUERY  + "WHERE is_conciliado = " + isConciliado +
            " ORDER BY e.data_transacao DESC;";
        return commonDAO.findList(query);
    }

    public void save(ExtratoDTO e) throws SQLException { commonDAO.save(e, UPDATE_QUERY, INSERT_QUERY); }
    public void saveAll(List<ExtratoDTO> list) throws SQLException { commonDAO.saveBatch(list, UPDATE_QUERY, INSERT_QUERY); }
    public void deleteById(long id) throws SQLException { commonDAO.deleteRecord(DELETE_QUERY, id); }
    public void deleteBatch(List<ExtratoDTO> list) throws SQLException { commonDAO.deleteBatch(DELETE_QUERY, list); }

    public ExtratoDTO getDTO(ResultSet rs) throws SQLException {
        String conta;
        if (rs.getLong("conta_id") != 0) {
            String nomeBanco = rs.getString("apelido_banco") != null ? rs.getString("apelido_banco") : rs.getString("nome_banco");
            conta = nomeBanco  +
                    " AG: " + rs.getString("agencia") +
                    " CC: " + rs.getString("numero_conta");
        } else conta = null;
        ExtratoDTO dto = new ExtratoDTO(
                rs.getLong("id"),
                rs.getLong("conta_id"),
                rs.getDate("data_transacao").toLocalDate(),
                conta,
                rs.getString("categoria_extrato"),
                rs.getString("descricao"),
                rs.getDouble("valor"),
                rs.getBoolean("is_conciliado")
        );
        return dto;
    }

    public void prepareValuesDTO(PreparedStatement ps, ExtratoDTO e) throws SQLException {
        ps.setDate(1, Date.valueOf(e.getDataTransacao()));
        ps.setLong(2, e.getContaId());
        ps.setString(3, e.getCategoriaExtrato());
        ps.setString(4, e.getDescricao());
        ps.setDouble(5, e.getValor());
        ps.setBoolean(6, e.isConciliado());
    }

    @Override
    public int getIdParameterIndex() { return 7; }

}
