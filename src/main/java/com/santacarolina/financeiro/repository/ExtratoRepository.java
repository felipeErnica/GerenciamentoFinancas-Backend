package com.santacarolina.financeiro.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.ExtratoEntity;

/**
 * ExtratoRepository
 */
@Repository
public interface ExtratoRepository extends JpaRepository<ExtratoEntity, Long> {

    @Query("""
        SELECT e
        FROM ExtratoEntity e
        WHERE e.conta.id = :contaId
    """)
    List<ExtratoEntity> findByContaId(long contaId);

    List<ExtratoEntity> findByConciliado(boolean isConciliado);
}
