package com.santacarolina.financeiro.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.DocumentoEntity;
import com.santacarolina.financeiro.enums.TipoDocumento;

/**
 * DocumentoRepository
 */
@Repository
public interface DocumentoRepository extends JpaRepository<DocumentoEntity, Long> {

    @Query("""
        SELECT d
        FROM DocumentoEntity d
        WHERE d.contato.id = :contatoId
            AND d.tipoDoc = :tipoDoc
            AND d.dataEmissao = :dataEmissao
            AND d.pasta.id = :pastaId
            AND d.valor = :valor
        """)
    Optional<DocumentoEntity> findEqual(long contatoId, TipoDocumento tipoDoc, LocalDate dataEmissao, long pastaId,
            double valor);

    @Query("""
        SELECT d
        FROM DocumentoEntity d
        WHERE d.contato.id = :contatoId AND d.numDoc = :numDoc
        """)
    Optional<DocumentoEntity> findNotaEqual(long contatoId, long numDoc);

}
