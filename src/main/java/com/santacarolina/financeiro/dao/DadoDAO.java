package com.santacarolina.financeiro.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.santacarolina.financeiro.dto.DadoDTO;
import com.santacarolina.financeiro.interfaces.DAO;
import com.santacarolina.financeiro.util.CommonDAO;
import com.santacarolina.financeiro.util.DataBaseConn;

@Component
public class DadoDAO implements DAO<DadoDTO> {

    private static final String SELECT_QUERY = """
            SELECT d.id, d.agencia, d.banco_id, d.numero_conta, d.contato_id, 
                c.nome as nome_contato,
                b.nome_banco
            FROM dados_bancarios d
                LEFT JOIN chaves_pix p ON p.conta_id = d.id
                LEFT JOIN contatos c ON c.id = d.contato_id
                LEFT JOIN bancos b ON b.id = d.banco_id
            """;
    private static final String UPDATE_QUERY = """
            UPDATE dados_bancarios
            SET agencia = ?, banco_id = ?, numero_conta = ?, contato_id = ?
            WHERE id = ?
            """;
    private static final String INSERT_QUERY = """
            INSERT INTO dados_bancarios (agencia, banco_id, numero_conta, contato_id)
            VALUES (?,?,?,?)
            """;
    private static final String DELETE_QUERY = "DELETE FROM dados_bancarios WHERE id = ?";

    private CommonDAO<DadoDTO> commonDAO;

    @Autowired
    public DadoDAO(DataBaseConn conn) { this.commonDAO = new CommonDAO<>(this, conn); }

    public Optional<DadoDTO> findById(long id) throws SQLException {
        String query = SELECT_QUERY + "WHERE d.id = " + id;
        return commonDAO.findOne(query);
    }

    public List<DadoDTO> getPixByContato(long contatoId) throws SQLException {
        String query = SELECT_QUERY + "WHERE d.contato_id = " + contatoId;
        return commonDAO.findList(query);
    }

    public Optional<DadoDTO> getEqual(String agencia, String numeroConta, long bancoId) throws SQLException {
        String query = SELECT_QUERY + "WHERE d.agencia = '" + agencia + "' AND " +
                " d.numero_conta = '" + numeroConta + "' AND " +
                " d.banco_id = " + bancoId;
        return commonDAO.findOne(query);
    }

    public List<DadoDTO> findAll() throws SQLException {
        String query = SELECT_QUERY + " ORDER BY nome_contato, numero_conta";
        return commonDAO.findList(query);
    }

    public DadoDTO save(DadoDTO d) throws SQLException { return commonDAO.save(d, UPDATE_QUERY, INSERT_QUERY); }
    public void deleteById(long id) throws SQLException { commonDAO.deleteRecord(DELETE_QUERY, id); }

    @Override
    public DadoDTO getDTO(ResultSet rs) throws SQLException {
        return new DadoDTO(
                rs.getLong("id"),
                rs.getString("agencia"),
                rs.getLong("banco_id"),
                rs.getString("numero_conta"),
                rs.getLong("contato_id"),
                rs.getString("nome_contato"),
                rs.getString("nome_banco")
        );
    }

    @Override
    public void prepareValuesDTO(PreparedStatement ps, DadoDTO d) throws SQLException {
        ps.setString(1, d.getAgencia());
        ps.setLong(2, d.getBancoId());
        ps.setString(3, d.getNumeroConta());
        ps.setLong(4, d.getContatoId());
    }

    @Override
    public int getIdParameterIndex() { return 5; }

}
