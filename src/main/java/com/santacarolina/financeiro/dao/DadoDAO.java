package com.santacarolina.financeiro.dao;

import com.santacarolina.financeiro.dto.DadoDTO;
import com.santacarolina.financeiro.interfaces.DAO;
import com.santacarolina.financeiro.util.CommonDAO;
import com.santacarolina.financeiro.util.DataBaseConn;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DadoDAO implements DAO<DadoDTO> {

    private static final String SELECT_QUERY = """
            SELECT id, agencia, banco_id, numero_conta, contato_id, pix_id
            FROM dados_bancarios
            """;
    private static final String UPDATE_QUERY = """
            UPDATE dados_bancarios
            SET agencia = ?, banco_id = ?, numero_conta = ?, contato_id = ?, pix_id = ?
            WHERE id = ?
            """;
    private static final String INSERT_QUERY = """
            INSERT INTO (agencia, banco_id, numero_conta, contato_id, pix_id)
            VALUES (?,?,?,?,?)
            """;
    private static final String DELETE_QUERY = "DELETE FROM dados_bancarios WHERE id = ?";

    private CommonDAO<DadoDTO> commonDAO;

    @Autowired
    public DadoDAO(DataBaseConn conn) {
        this.commonDAO = new CommonDAO<>(this, conn);
    }

    public List<DadoDTO> findAll() throws SQLException {
        return commonDAO.findList(SELECT_QUERY);
    }

    public Optional<DadoDTO> findById(long id) throws SQLException {
        String query = SELECT_QUERY + "WHERE id = " + id;
        return commonDAO.findOne(query);
    }

    public List<DadoDTO> getPixByContato(long contatoId) throws SQLException {
        String query = SELECT_QUERY + "WHERE contato_id = " + contatoId;
        return commonDAO.findList(query);
    }

    public Optional<DadoDTO> getEqual(String agencia, String numeroConta, long bancoId) throws SQLException {
        String query = SELECT_QUERY + "WHERE agencia = " + agencia +
                " numero_conta = " + numeroConta +
                " banco_id = " + bancoId;
        return commonDAO.findOne(query);
    }

    public void save(DadoDTO d) throws SQLException { commonDAO.save(d, UPDATE_QUERY, INSERT_QUERY); }
    public void deleteById(long id) throws SQLException { commonDAO.deleteRecord(DELETE_QUERY, id); }

    @Override
    public DadoDTO getDTO(ResultSet rs) throws SQLException {
        return new DadoDTO(
                rs.getLong("id"),
                rs.getString("agencia"),
                rs.getLong("banco_id"),
                rs.getString("numero_conta"),
                rs.getLong("contato_id"),
                rs.getLong("pix_id")
        );
    }

    @Override
    public void prepareValuesDTO(PreparedStatement ps, DadoDTO d) throws SQLException {
        ps.setString(1, d.getAgencia());
        ps.setLong(2, d.getBancoId());
        ps.setString(3, d.getNumeroConta());
        ps.setLong(4, d.getContatoId());
        ps.setLong(5, d.getPixId());
    }

    @Override
    public int getIdParameterIndex() { return 6; }

}