package com.santacarolina.financeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.ConciliacaoEntity;

/**
 * ConciliacaoRepository
 */
@Repository
public interface ConciliacaoRepository extends JpaRepository<ConciliacaoEntity, Long>{
    
    @Override
    @Query("""
        SELECT c, d, e
        FROM ConciliacaoEntity c
        LEFT JOIN ExtratoEntity e ON c.extrato.id = e.id
        LEFT JOIN DuplicataEntity d ON d.id = c.duplicata.id
        ORDER BY e.dataTransacao DESC
        """)
    List<ConciliacaoEntity> findAll();
}
