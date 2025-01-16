package com.santacarolina.financeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.dto.DuplicataDTO;
import com.santacarolina.financeiro.entity.DuplicataEntity;

/**
 * DuplicataRepository
 */
@Repository
public interface DuplicataRepository extends JpaRepository<DuplicataEntity, Long> {
    List<DuplicataDTO> findByPaga(boolean paga);

    @Query("""
        SELECT d
        FROM DuplicataEntity d
        WHERE d.documento.id = :documentoId
        """)
    List<DuplicataEntity> findByDoc(long documentoId);
}
