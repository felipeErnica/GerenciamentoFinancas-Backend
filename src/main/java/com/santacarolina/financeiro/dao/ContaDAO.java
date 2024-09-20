package com.santacarolina.financeiro.dao;

import com.santacarolina.financeiro.dto.ContaDTO;
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
public class ContaDAO implements DAO<ContaDTO> {

    private static final String INSERT_QUERY = """
            INSERT INTO public.contas_bancarias(
            	banco_id, agencia, numero_conta, nome_conta)
            	VALUES (?, ?, ?, ?, ?, ?);
            """;
    private static final String SELECT_QUERY = """
            SELECT id, banco_id, agencia, numero_conta, nome_conta
            	FROM contas_bancarias
            """;
    private static final String UPDATE_QUERY = """
            UPDATE contas_bancarias
            	SET banco_id=?, agencia=?, numero_conta=?, nome_conta=?
            	WHERE id = ?;
            """;
    private static final String DELETE_QUERY = "DELETE FROM contas_bancarias WHERE id = ?";

    private CommonDAO<ContaDTO> commonDAO;

    @Autowired
    public ContaDAO(DataBaseConn conn) { this.commonDAO = new CommonDAO<>(this,conn); }

    public List<ContaDTO> findAll() throws SQLException { return commonDAO.findList(SELECT_QUERY); }

    public Optional<ContaDTO> findEqual(String agencia, String numeroConta, long bancoId) throws SQLException {
        String query = SELECT_QUERY + """
                WHERE agencia = %s AND numero_conta = %s + banco_id = %s;
                """.formatted(agencia, numeroConta, bancoId);
        return commonDAO.findOne(query);
    }

    public Optional<ContaDTO> findById(long id) throws SQLException {
        String query = SELECT_QUERY + "WHERE id = " + id;
        return commonDAO.findOne(query);
    }

    public void save(ContaDTO c) throws SQLException { commonDAO.save(c, UPDATE_QUERY, INSERT_QUERY); }
    public void deleteById(long id) throws SQLException { commonDAO.deleteRecord(DELETE_QUERY, id); }


    @Override
    public ContaDTO getDTO(ResultSet rs) throws SQLException {
        ContaDTO d = new ContaDTO();
        d.setId(rs.getLong("id"));
        d.setAgencia(rs.getString("agencia"));
        d.setNomeConta(rs.getString("nome_conta"));
        d.setNumeroConta(rs.getString("numero_conta"));
        d.setBancoId(rs.getLong("banco_id"));
        return d;
    }

    @Override
    public void prepareValuesDTO(PreparedStatement ps, ContaDTO d) throws SQLException {
        ps.setLong(1, d.getBancoId());
        ps.setString(2, d.getAgencia());
        ps.setString(3, d.getNumeroConta());
        ps.setString(4, d.getNomeConta());
    }

    @Override
    public int getIdParameterIndex() { return 5; }

}
