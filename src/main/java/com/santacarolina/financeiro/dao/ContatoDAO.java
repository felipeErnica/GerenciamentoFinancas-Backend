package com.santacarolina.financeiro.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.santacarolina.financeiro.dto.ContatoDTO;
import com.santacarolina.financeiro.interfaces.DAO;
import com.santacarolina.financeiro.util.CommonDAO;
import com.santacarolina.financeiro.util.DataBaseConn;

@Component
public class ContatoDAO implements DAO<ContatoDTO> {

    private static final String SELECT_QUERY  = """
            SELECT id, nome, cpf, cnpj, ie
            FROM contatos
            """;
    private static final String INSERT_QUERY  = """
            INSERT INTO contatos (nome, cpf, cnpj, ie)
                VALUES(?,?,?,?);
            """;
    private static final String UPDATE_QUERY  = """
            UPDATE contatos
                SET nome = ?, cpf = ?, cnpj = ?, ie =  ?
                WHERE id = ?;
            """;
    private static final String DELETE_QUERY  = "DELETE FROM contatos WHERE id = ?";

    private CommonDAO<ContatoDTO> commonDAO;

    @Autowired
    public ContatoDAO(DataBaseConn conn) { this.commonDAO = new CommonDAO<>(this, conn); }

    public Optional<ContatoDTO> findByCpf(String cpf) throws SQLException {
        String query = SELECT_QUERY + "WHERE cpf = '" + cpf + "'";
        return commonDAO.findOne(query);
    }

    public Optional<ContatoDTO> findByCnpj(String cnpj) throws SQLException {
        String query = SELECT_QUERY + "WHERE cnpj = '" + cnpj + "'";
        return commonDAO.findOne(query);
    }

    public Optional<ContatoDTO> findByIe(String ie) throws SQLException {
        String query = SELECT_QUERY + "WHERE ie = '" + ie + "'";
        return commonDAO.findOne(query);
    }

    public Optional<ContatoDTO> findById(long id) throws SQLException {
        String query = SELECT_QUERY + "WHERE id = " + id;
        return commonDAO.findOne(query);
    }

    public Optional<ContatoDTO> getByNome(String nome) throws SQLException {
        String query = SELECT_QUERY + "WHERE nome = '" + nome.replace("+", " ") + "'";
        return commonDAO.findOne(query);
    }

    public List<ContatoDTO> findAll() throws SQLException {
        String query = SELECT_QUERY + "ORDER BY nome";
        return commonDAO.findList(query); 
    }

    public void saveAll(List<ContatoDTO> contatos) throws SQLException { commonDAO.saveBatch(contatos, UPDATE_QUERY, INSERT_QUERY); }
    public ContatoDTO save(ContatoDTO contato) throws SQLException { return commonDAO.save(contato, UPDATE_QUERY, SELECT_QUERY); }
    public void deleteById(long id) throws SQLException { commonDAO.deleteRecord(DELETE_QUERY, id); }

    @Override
    public ContatoDTO getDTO(ResultSet rs) throws SQLException {
        return new ContatoDTO(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("cpf"),
                rs.getString("cnpj"),
                rs.getString("ie")
        );
    }

    @Override
    public void prepareValuesDTO(PreparedStatement ps, ContatoDTO c) throws SQLException {
        ps.setString(1, c.getNome());
        ps.setString(2, c.getCpf());
        ps.setString(3, c.getCnpj());
        ps.setString(4, c.getIe());
    }

    @Override
    public int getIdParameterIndex() { return 5; }

}
