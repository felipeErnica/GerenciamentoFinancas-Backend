package com.santacarolina.financeiro.util;

import com.santacarolina.financeiro.interfaces.DAO;
import com.santacarolina.financeiro.interfaces.DataDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommonDAO<T extends DataDAO> {

    private final Logger logger;
    private DAO<T> dao;
    private DataBaseConn conn;
    private ResultSet rs;
    private PreparedStatement ps;

    public CommonDAO(DAO<T> dao, DataBaseConn conn) {
        this.conn = conn;
        this.dao = dao;
        logger = LogManager.getLogger(dao.getClass());
    }

    public Optional<T> findOne(String query) throws SQLException {
        try {
            rs = conn.getResultSet(query);
            rs.next();
            Optional<T> optionalT = Optional.ofNullable(dao.getDTO(rs));
            rs.close();
            conn.closeConn();
            return optionalT;
        } catch (SQLException e) {
            throwError(e);
            throw new SQLException(e);
        }
    }

    public List<T> findList(String query) throws SQLException {
        try {
            rs = conn.getResultSet(query);
            List<T> list = new ArrayList<>();
            while (rs.next()) {
                T t = dao.getDTO(rs);
                list.add(t);
            }
            rs.close();
            conn.closeConn();
            return list;
        } catch (SQLException e) {
            throwError(e);
            throw new SQLException();
        }
    }

    public void save(T t, String updateQuery, String insertQuery) throws SQLException {
        try {
            if (t.getId() != 0) {
                ps = conn.getStatement(updateQuery);
                dao.prepareValuesDTO(ps, t);
                ps.setLong(dao.getIdParameterIndex(), t.getId());
            } else {
                ps = conn.getStatement(insertQuery);
                dao.prepareValuesDTO(ps, t);
            }
            ps.executeUpdate();
            ps.close();
            conn.closeConn();
        } catch (SQLException e) {
            throwError(e);
            throw new SQLException(e);
        }
    }

    public void saveBatch(List<T> list, String updateQuery, String insertQuery) throws SQLException {
        try {
            List<T> listUpdate = new ArrayList<>();
            List<T> listInsert = new ArrayList<>();

            for (T t : list) {
                if (t.getId() == 0) listInsert.add(t);
                else listUpdate.add(t);
            }

            if (!listUpdate.isEmpty()) {
                ps = conn.getStatement(updateQuery);
                for (T t : listUpdate) {
                    dao.prepareValuesDTO(ps, t);
                    ps.setLong(dao.getIdParameterIndex(), t.getId());
                    ps.addBatch();
                }
                ps.executeBatch();
                ps.close();
            }

            if (!listInsert.isEmpty()) {
                ps = conn.getStatement(insertQuery);
                for (T t : listInsert) {
                    dao.prepareValuesDTO(ps, t);
                    ps.addBatch();
                }
                ps.executeBatch();
                ps.close();
            }

            conn.closeConn();
        } catch (SQLException e) {
            throwError(e);
            throw new SQLException(e);
        }
    }

    public void deleteRecord(String query, long id) throws SQLException {
        try {
            ps = conn.getStatement(query);
            ps.setLong(1, id);
            ps.execute();
            ps.close();
            conn.closeConn();
        } catch (SQLException e) {
            throwError(e);
            throw new SQLException(e);
        }
    }

    public void deleteBatch(String query, List<T> list) throws SQLException {
        try {
            ps = conn.getStatement(query);
            for (T t : list) {
                ps.setLong(1, t.getId());
                ps.addBatch();
            }
            ps.executeBatch();
            ps.close();
            conn.closeConn();
        } catch (SQLException e) {
            throwError(e);
            throw new SQLException(e);
        }
    }

    private void throwError(SQLException e) throws SQLException {
        logger.error(e);
        conn.closeConn();
    }

}
