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
        SELECT dup, doc, dado, pix, contato, pasta
        FROM DuplicataEntity dup
        LEFT JOIN DocumentoEntity doc ON doc.id = dup.documento.id
        LEFT JOIN DadoEntity dado ON dado.id = dup.dado.id
        LEFT JOIN PixEntity pix ON pix.id = dup.pix.id
        LEFT JOIN ContatoEntity ON contato.id = doc.emissor.id
        LEFT JOIN PastaEntity ON pasta.id = doc.pasta.id
        ORDER BY dup.dataVencimento DESC
        """) 
    List<DuplicataEntity> findAll();

    @Override
    @Query("""
        SELECT dup, doc, dado, pix
        FROM DuplicataEntity dup
        LEFT JOIN DocumentoEntity doc ON doc.id = dup.documento.id
        LEFT JOIN DadoEntity dado ON dado.id = dup.dado.id
        LEFT JOIN PixEntity pix ON pix.id = dup.pix.id
        LEFT JOIN ContatoEntity ON contato.id = doc.emissor.id
        LEFT JOIN PastaEntity ON pasta.id = doc.pasta.id
        WHERE dup.id = :id
        """) 
    Optional<DuplicataEntity> findById(Long id);

    @Query("""
        SELECT dup, doc, dado, pix
        FROM DuplicataEntity dup
        LEFT JOIN DocumentoEntity doc ON doc.id = dup.documento.id
        LEFT JOIN DadoEntity dado ON dado.id = dup.dado.id
        LEFT JOIN PixEntity pix ON pix.id = dup.pix.id
        LEFT JOIN ContatoEntity ON contato.id = doc.emissor.id
        LEFT JOIN PastaEntity ON pasta.id = doc.pasta.id
        WHERE dup.paga = :paga
        """) 
    List<DuplicataEntity> findByPaga(boolean paga);

    @Query("""
        SELECT dup, doc, dado, pix
        FROM DuplicataEntity dup
        LEFT JOIN DocumentoEntity doc ON doc.id = dup.documento.id
        LEFT JOIN DadoEntity dado ON dado.id = dup.dado.id
        LEFT JOIN PixEntity pix ON pix.id = dup.pix.id
        LEFT JOIN ContatoEntity ON contato.id = doc.emissor.id
        LEFT JOIN PastaEntity ON pasta.id = doc.pasta.id
        WHERE dup.documento.id = :documentoId
        ORDER BY dup.dataVencimento
        """)
    List<DuplicataEntity> findByDoc(long documentoId);
}
