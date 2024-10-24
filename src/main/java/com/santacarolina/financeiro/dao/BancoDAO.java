package com.santacarolina.financeiro.dao;

import com.santacarolina.financeiro.dto.BancoDTO;
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
public class BancoDAO implements DAO<BancoDTO> {

    private static final String SELECT_QUERY = """
            SELECT id, nome_banco, apelido_banco 
            FROM BANCOS
            """;
    private static final String UPDATE_QUERY = """
            UPDATE bancos
            SET nome_banco = ?, apelido_banco = ?
            WHERE id = ?;
            """;
    private static final String INSERT_QUERY = "INSERT INTO bancos(nome_banco, apelido_banco) VALUES(?,?)";
    private static final String DELETE_QUERY = "DELETE FROM bancos WHERE id = ?";

    private CommonDAO<BancoDTO> commonDAO;

    @Autowired
    public BancoDAO(DataBaseConn conn) { this.commonDAO = new CommonDAO<>(this, conn); }

    public List<BancoDTO> findAll() throws SQLException { 
        String query = SELECT_QUERY + " ORDER BY nomeBanco;";
        return commonDAO.findList(query); 
    }

    public Optional<BancoDTO> findById(long id) throws SQLException {
        String query = SELECT_QUERY + "WHERE id = " + id;
        return commonDAO.findOne(query);
    }

    public Optional<BancoDTO> findByNomeBanco(String nomeBanco) throws SQLException {
        String query = SELECT_QUERY + "WHERE nome_banco = " + nomeBanco;
        return commonDAO.findOne(query);
    }

    public void save(BancoDTO banco) throws SQLException { commonDAO.save(banco, UPDATE_QUERY, INSERT_QUERY); }
    public void deleteById(long id) throws SQLException { commonDAO.deleteRecord(DELETE_QUERY, id); }

    @Override
    public BancoDTO getDTO(ResultSet rs) throws SQLException {
        return new BancoDTO(rs.getLong("id"), rs.getString("nome_banco"), rs.getString("apelido_banco"));
    }

    @Override
    public void prepareValuesDTO(PreparedStatement ps, BancoDTO b) throws SQLException {
        ps.setString(1, b.getNomeBanco());
        ps.setString(2, b.getApelidoBanco());
    }

    @Override
    public int getIdParameterIndex() { return 3; }

}
