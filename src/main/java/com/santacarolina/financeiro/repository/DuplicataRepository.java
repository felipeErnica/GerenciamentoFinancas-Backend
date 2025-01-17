package com.santacarolina.financeiro.repository;

import java.util.List;
import java.util.Optional;

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
        SELECT dup, doc, conta, p
        FROM DuplicataEntity dup
        LEFT JOIN DocumentoEntity doc ON doc.id = dup.documento.id
        LEFT JOIN DadoEntity conta ON conta.id = doc.dado.id
        LEFT JOIN PixEntity p ON p.id = doc.pix.id
        ORDER BY dup.dataVencimento DESC
        """) 
    List<DuplicataEntity> findAll();

    @Override
    @Query("""
        SELECT dup, doc, conta, p
        FROM DuplicataEntity dup
        LEFT JOIN DocumentoEntity doc ON doc.id = dup.documento.id
        LEFT JOIN DadoEntity conta ON conta.id = doc.dado.id
        LEFT JOIN PixEntity p ON p.id = doc.pix.id
        WHERE dup.id = :id
        """) 
    Optional<DuplicataEntity> findById(Long id);

    @Query("""
        SELECT dup, doc, conta, p
        FROM DuplicataEntity dup
        LEFT JOIN DocumentoEntity doc ON doc.id = dup.documento.id
        LEFT JOIN DadoEntity conta ON conta.id = doc.dado.id
        LEFT JOIN PixEntity p ON p.id = doc.pix.id
        WHERE dup.paga = :paga
        """) 
    List<DuplicataEntity> findByPaga(boolean paga);

    @Query("""
        SELECT dup, doc, conta, p
        FROM DuplicataEntity dup
        LEFT JOIN DocumentoEntity doc ON doc.id = dup.documento.id
        LEFT JOIN DadoEntity conta ON conta.id = doc.dado.id
        LEFT JOIN PixEntity p ON p.id = doc.pix.id
        WHERE d.documento.id = :documentoId
        ORDER BY dup.dataVencimento
        """)
    List<DuplicataEntity> findByDoc(long documentoId);
}
