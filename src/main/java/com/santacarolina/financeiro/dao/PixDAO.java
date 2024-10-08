package com.santacarolina.financeiro.dao;

import com.santacarolina.financeiro.dto.PixDTO;
import com.santacarolina.financeiro.enums.TipoPix;
import com.santacarolina.financeiro.interfaces.DAO;
import com.santacarolina.financeiro.util.CommonDAO;
import com.santacarolina.financeiro.util.DataBaseConn;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class PixDAO implements DAO<PixDTO> {

    private static final String SELECT_QUERY = """
            SELECT c.id, c.contato_id, c.conta_id, c.tipo_pix, c.chave, 
                d.agencia, d.numero_conta,
                b.nome_banco 
            FROM chaves_pix c
                LEFT JOIN dados_bancarios d ON d.id = c.conta_id
                LEFT JOIN bancos b ON b.id = d.banco_id
            """;
    private static final String UPDATE_QUERY = """
            UPDATE chaves_pix 
            SET contato_id = ?, conta_id = ?, tipo_pix = ?, chave = ?
            WHERE id  = ?;
            """;
    private static final String INSERT_QUERY = """
            INSERT INTO chaves_pix (contato_id, conta_id, tipo_pix, chave)
                VALUES (?, ?, ?, ?)
            """;
    private static final String DELETE_QUERY = "DELETE FROM chaves_pix WHERE id = ?;";

    private CommonDAO<PixDTO> commonDAO;

    public PixDAO(DataBaseConn conn) { this.commonDAO = new CommonDAO<>(this, conn); }

    public Optional<PixDTO> findById(long id) throws SQLException {
        String query = SELECT_QUERY  + "WHERE c.id = " + id;
        return commonDAO.findOne(query);
    }

    public Optional<PixDTO> findByChavePix(String chave) throws SQLException {
        String query = SELECT_QUERY + "WHERE c.chave = '" + chave + "'";
        return commonDAO.findOne(query);
    }

    public List<PixDTO> findByContato(long contatoId) throws SQLException {
        String query = SELECT_QUERY + "WHERE c.contato_id = " + contatoId;
        return commonDAO.findList(query);
    }

    public List<PixDTO> findAll() throws SQLException { return commonDAO.findList(SELECT_QUERY); }
    public void save(PixDTO chavePix) throws SQLException { commonDAO.save(chavePix, UPDATE_QUERY, INSERT_QUERY); }
    public void delete(long id) throws SQLException { commonDAO.deleteRecord(DELETE_QUERY, id); }

    @Override
    public PixDTO getDTO(ResultSet rs) throws SQLException {
        return new PixDTO(
                rs.getLong("id"),
                rs.getLong("contato_id"),
                rs.getLong("conta_id"),
                TipoPix.fromValue(rs.getInt("tipo_pix")),
                rs.getString("chave"),
                rs.getString("nome_banco"),
                rs.getString("agencia"),
                rs.getString("numero_conta")
        );
    }

    @Override
    public void prepareValuesDTO(PreparedStatement ps, PixDTO p) throws SQLException {
        ps.setLong(1, p.getContatoId());
        ps.setLong(2, p.getDadoId());
        ps.setInt(3, p.getTipoPix().getValue());
        ps.setString(4, p.getChave());
    }

    @Override
    public int getIdParameterIndex() { return 5; }

}
