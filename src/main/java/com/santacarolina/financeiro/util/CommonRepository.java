package com.santacarolina.financeiro.util;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santacarolina.financeiro.interfaces.DataDAO;

import jakarta.persistence.OptimisticLockException;

/**
 * CommonRepository
 */
public class CommonRepository<T extends DataDAO> {

    private JpaRepository<T, Long> jpaRepository;

    public CommonRepository(JpaRepository<T, Long> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
    
    public void deleteAll(List<T> list) throws OptimisticLockException {
    }
}
