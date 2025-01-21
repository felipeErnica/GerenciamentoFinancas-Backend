package com.santacarolina.financeiro.repository;

import java.time.LocalDate;
import java.util.List;
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
        SELECT doc, pasta, contato, conta, banco
        FROM DocumentoEntity doc
        LEFT JOIN PastaEntity pasta ON pasta.id = doc.pasta.id
        LEFT JOIN ContatoEntity contato ON contato.id = doc.contato.id
        LEFT JOIN ContaEntity conta ON conta.id = pasta.conta.id
        LEFT JOIN BancoEntity banco ON banco.id = conta.banco.id
        ORDER BY doc.dataEmissao DESC
        """)
    List<DocumentoEntity> findAll();

    @Query("""
        SELECT doc, pasta, contato, conta, banco
        FROM DocumentoEntity doc
        LEFT JOIN PastaEntity pasta ON pasta.id = doc.pasta.id
        LEFT JOIN ContatoEntity contato ON contato.id = doc.contato.id
        LEFT JOIN ContaEntity conta ON conta.id = pasta.conta.id
        LEFT JOIN BancoEntity banco ON banco.id = conta.banco.id
        WHERE doc.contato.id = :contatoId
            AND doc.tipoDoc = :tipoDoc
            AND doc.dataEmissao = :dataEmissao
            AND doc.pasta.id = :pastaId
            AND doc.valor = :valor
        """)
    Optional<DocumentoEntity> findEqual(long contatoId, TipoDocumento tipoDoc, LocalDate dataEmissao, long pastaId,
            double valor);

    @Query("""
        SELECT doc, pasta, contato, conta, banco
        FROM DocumentoEntity doc
        LEFT JOIN PastaEntity pasta ON pasta.id = doc.pasta.id
        LEFT JOIN ContatoEntity contato ON contato.id = doc.contato.id
        LEFT JOIN ContaEntity conta ON conta.id = pasta.conta.id
        LEFT JOIN BancoEntity banco ON banco.id = conta.banco.id
        WHERE doc.contato.id = :contatoId AND doc.numDoc = :numDoc
        """)
    Optional<DocumentoEntity> findNotaEqual(long contatoId, long numDoc);

}
