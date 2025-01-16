package com.santacarolina.financeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.DuplicataEntity;

/**
 * DuplicataRepository
 */
@Repository
public interface DuplicataRepository extends JpaRepository<DuplicataEntity, Long> {
    
    @Override
    @Query("""
        SELECT d
        FROM DuplicataEntity d
        LEFT JOIN FETCH d.documento.id
        LEFT JOIN FETCH d.pix.id
        LEFT JOIN FETCH d.dado.id
        """)
    List<DuplicataEntity> findAll();

    List<DuplicataEntity> findByPaga(boolean paga);

    @Query("""
        SELECT d
        FROM DuplicataEntity d
        WHERE d.documento.id = :documentoId
        """)
    List<DuplicataEntity> findByDoc(long documentoId);
}
