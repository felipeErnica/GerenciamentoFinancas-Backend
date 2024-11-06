package com.santacarolina.financeiro.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.santacarolina.financeiro.dto.PastaDTO;
import com.santacarolina.financeiro.interfaces.DAO;
import com.santacarolina.financeiro.util.CommonDAO;
import com.santacarolina.financeiro.util.DataBaseConn;

@Component
public class PastaDAO implements DAO<PastaDTO> {

    private static final String SELECT_QUERY = """
            SELECT id, nome, caminho_pasta, conta_id
            FROM pastas_contabeis
            """;
    private static final String UPDATE_QUERY = """
            UPDATE pastas_contabeis
            SET nome = ?, caminho_pasta = ?, conta_id = ?
            WHERE id = ?;
            """;
    private static final String INSERT_QUERY = """
            INSERT INTO pastas_contabeis(nome, caminho_pasta, conta_id)
            VALUES (?,?,?)
            """;
    private static final String DELETE_QUERY = "DELETE FROM pastas_contabeis WHERE id = ?;";

    private CommonDAO<PastaDTO> commonDAO;

    @Autowired
    public PastaDAO(DataBaseConn conn) { this.commonDAO = new CommonDAO<>(this, conn); }

    public List<PastaDTO> findAll() throws SQLException { 
        String query = SELECT_QUERY + " ORDER BY nome";
        return commonDAO.findList(query); 
    }

    public Optional<PastaDTO> findByNome(String nome) throws SQLException {
        String query = SELECT_QUERY + "WHERE nome = '" + nome + "'";
        return commonDAO.findOne(query);
    }

    public Optional<PastaDTO> findById(long id) throws SQLException {
        String query  = SELECT_QUERY + "WHERE id = " + id;
        return commonDAO.findOne(query);
    }

    public void save(PastaDTO pasta) throws SQLException { commonDAO.save(pasta, UPDATE_QUERY, INSERT_QUERY); }
    public void deleteById(long id) throws SQLException { commonDAO.deleteRecord(DELETE_QUERY, id); }

    @Override
    public PastaDTO getDTO(ResultSet rs) throws SQLException {
        return new PastaDTO(
                rs.getLong("id"),
                rs.getString("nome"),
                rs.getString("caminho_pasta"),
                rs.getLong("conta_id")
        );
    }

    @Override
    public void prepareValuesDTO(PreparedStatement ps, PastaDTO p) throws SQLException {
        ps.setString(1, p.getNome());
        ps.setString(2, p.getCaminhoPasta());
        ps.setLong(3, p.getContaId());
    }

    @Override
    public int getIdParameterIndex() { return 4; }

}
