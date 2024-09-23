package com.santacarolina.financeiro.dao;

import com.santacarolina.financeiro.dto.ConciliacaoDTO;
import com.santacarolina.financeiro.enums.TipoMovimento;
import com.santacarolina.financeiro.interfaces.DAO;
import com.santacarolina.financeiro.util.CommonDAO;
import com.santacarolina.financeiro.util.DataBaseConn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ConciliacaoDAO implements DAO<ConciliacaoDTO> {

    private static final String UPDATE_QUERY = """
        UPDATE conciliacoes 
        SET tipo_movimento = ?, duplicata_id = ?, extrato_id = ? 
        WHERE id = ?
        """;
    private static final String INSERT_QUERY = """
        INSERT INTO conciliacoes(tipo_movimento, duplicata_id, extrato_id) 
        VALUES (?,?,?)
        """;

    private DataBaseConn conn;
    private CommonDAO<ConciliacaoDTO> commonDAO;

    @Autowired
    public ConciliacaoDAO(DataBaseConn conn) {
        this.conn = conn;
        this.commonDAO = new CommonDAO<>(this, conn);
    }

    public void save(ConciliacaoDTO c) throws SQLException { commonDAO.save(c, UPDATE_QUERY, INSERT_QUERY); }
    public void saveAll(List<ConciliacaoDTO> list) throws SQLException { commonDAO.saveBatch(list, UPDATE_QUERY, INSERT_QUERY); }

    @Override
    public ConciliacaoDTO getDTO(ResultSet rs) throws SQLException {
        return new ConciliacaoDTO(
                rs.getLong("id"),
                TipoMovimento.fromValue(rs.getInt("tipo_movimento")),
                rs.getLong("duplicata_id"),
                rs.getLong("extrato_id"));
    }

    @Override
    public void prepareValuesDTO(PreparedStatement ps, ConciliacaoDTO dto) throws SQLException {
        ps.setInt(1, dto.getTipoMovimento().getValue());
        ps.setObject(2, dto.getDuplicataId());
        ps.setLong(3, dto.getExtratoId());
    }

    @Override
    public int getIdParameterIndex() { return 4; }

}
