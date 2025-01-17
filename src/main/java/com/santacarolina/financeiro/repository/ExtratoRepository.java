package com.santacarolina.financeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.ExtratoEntity;

/**
 * ExtratoRepository
 */
@Repository
public interface ExtratoRepository extends JpaRepository<ExtratoEntity, Long> {

    @Query("""
        SELECT e, c, b
        FROM ExtratoEntity e
        LEFT JOIN ContaEntity c ON c.id = e.conta.id
        LEFT JOIN BancoEntity b ON b.id = c.banco.id
        WHERE e.conta.id = :contaId
        ORDER BY e.dataTransacao DESC
    """)
    List<ExtratoEntity> findByContaId(long contaId);

    @Query("""
        SELECT e, c, b
        FROM ExtratoEntity e
        LEFT JOIN ContaEntity c ON c.id = e.conta.id
        LEFT JOIN BancoEntity b ON b.id = c.banco.id
        WHERE e.conciliado = :isConciliado
        ORDER BY e.dataTransacao DESC
    """)
    List<ExtratoEntity> findByConciliado(boolean isConciliado);
}
