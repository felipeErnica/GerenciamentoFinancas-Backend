package com.santacarolina.financeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.ProdutoEntity;

/**
 * ProdutoRepository
 */
@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    @Query("""
        SELECT p
        FROM ProdutoEntity p
        WHERE p.documento.id = :documentoId
        """)
    List<ProdutoEntity> findByDoc(long documentoId);
}
