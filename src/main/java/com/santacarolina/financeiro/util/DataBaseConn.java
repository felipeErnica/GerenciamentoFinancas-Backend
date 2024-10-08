package com.santacarolina.financeiro.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class DataBaseConn {

    @Autowired
    private DataSource dataSource;
    private Connection conn;
    private Logger logger = LogManager.getLogger();

    public ResultSet getResultSet(String query) throws SQLException {
        logger.info("Query enviada:\n" + query);
        conn = dataSource.getConnection();
        return conn.prepareStatement(query).executeQuery();
    }

    public PreparedStatement getStatement(String query) throws SQLException {
        logger.info("Query enviada:\n" + query);
        conn = dataSource.getConnection();
        return conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    }

    public void closeConn() throws SQLException { conn.close(); }
}