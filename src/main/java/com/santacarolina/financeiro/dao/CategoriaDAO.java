package com.santacarolina.financeiro.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.santacarolina.financeiro.dto.CategoriaDTO;
import com.santacarolina.financeiro.interfaces.DAO;
import com.santacarolina.financeiro.util.CommonDAO;
import com.santacarolina.financeiro.util.DataBaseConn;

/**
 * CategoriaDAO
 */
public class CategoriaDAO implements DAO<CategoriaDTO> {

    private static final String SELECT_QUERY = """
        SELECT id, fluxo_caixa, numero_categoria, nome
        FROM categorias_contabeis
    """;

    private static final String INSERT_QUERY = """
        INSERT INTO categorias_contabeis(fluxo_caixa, numero_categoria, nome)
            VALUES(?,?,?)
    """;

    private static final String UPDATE_QUERY = """
        UPDATE categorias_contabeis
        SET fluxo_caixa = ?, numero_categoria = ?, nome = ?
        WHERE id = ?;
    """;

    private static final String DELETE_QUERY = """
        DELETE FROM categorias_contabeis WHERE id = ?;
    """;

    private CommonDAO<CategoriaDTO> commonDAO;

    @Autowired
    public CategoriaDAO(DataBaseConn conn) {
        this.commonDAO = new CommonDAO<>(this, conn);
    }

    public Optional<CategoriaDTO> findById(long id) throws SQLException {
        String query = SELECT_QUERY + " WHERE id = " + id;
        return commonDAO.findOne(query);
    }

    public Optional<CategoriaDTO> findByNome(String nome) throws SQLException {
        String query = SELECT_QUERY + " WHERE nome = '" + nome.replace("+", " ") + "'";
        return commonDAO.findOne(query);
    }

    public Optional<CategoriaDTO> findByNumero(String numero) throws SQLException {
        String query = SELECT_QUERY + " WHERE numero_categoria = '" + numero + "'";
        return commonDAO.findOne(query);
    }

    public List<CategoriaDTO> findAll() throws SQLException {
        String query = SELECT_QUERY + " ORDER BY numero_categoria";
        return commonDAO.findList(query);
    }

    public void save(CategoriaDTO dto) throws SQLException {
        commonDAO.save(dto, UPDATE_QUERY, INSERT_QUERY);
    }

    public void deleteById(long id) throws SQLException {
        commonDAO.deleteRecord(DELETE_QUERY, id);
    }

    @Override
    public CategoriaDTO getDTO(ResultSet rs) throws SQLException { return null; }

    @Override
    public void prepareValuesDTO(PreparedStatement ps, CategoriaDTO t) throws SQLException {
        ps.setLong(1, t.getFluxoCaixa().getValue());
        ps.setString(2,t.getNumeroCategoria());
        ps.setString(3, t.getNome());
    }

    @Override
    public int getIdParameterIndex() { return 4; }

}
