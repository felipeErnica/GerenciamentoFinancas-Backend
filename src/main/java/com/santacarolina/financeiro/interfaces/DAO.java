package com.santacarolina.financeiro.interfaces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DAO<T extends DataDAO> {
    T getDTO(ResultSet rs) throws SQLException;
    void prepareValuesDTO(PreparedStatement ps, T t) throws SQLException;
    int getIdParameterIndex();
}
