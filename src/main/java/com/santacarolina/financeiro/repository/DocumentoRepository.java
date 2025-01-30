package com.santacarolina.financeiro.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.dto.DocumentoDTO;
import com.santacarolina.financeiro.entity.DocumentoEntity;
import com.santacarolina.financeiro.entity.UserEntity;
import com.santacarolina.financeiro.enums.TipoDocumento;

/**
 * DocumentoRepository
 */
@Repository
public interface DocumentoRepository extends JpaRepository<DocumentoEntity, Long> {

    @Query("""
        SELECT doc, pasta, emissor, conta, banco
        FROM DocumentoEntity doc
        LEFT JOIN PastaEntity pasta ON pasta.id = doc.pasta.id
        LEFT JOIN ContatoEntity emissor ON emissor.id = doc.emissor.id
        LEFT JOIN ContaEntity conta ON conta.id = pasta.conta.id
        LEFT JOIN BancoEntity banco ON banco.id = conta.banco.id
        ORDER BY doc.dataEmissao DESC
        WHERE doc.user = :user
        """)
    List<DocumentoEntity> findByUser(UserEntity user);

    @Query("""
        SELECT doc, pasta, emissor, conta, banco
        FROM DocumentoEntity doc
        LEFT JOIN PastaEntity pasta ON pasta.id = doc.pasta.id
        LEFT JOIN ContatoEntity emissor ON emissor.id = doc.emissor.id
        LEFT JOIN ContaEntity conta ON conta.id = pasta.conta.id
        LEFT JOIN BancoEntity banco ON banco.id = conta.banco.id
        WHERE doc.emissor.id = :contatoId
            AND doc.tipoDoc = :tipoDoc
            AND doc.dataEmissao = :dataEmissao
            AND doc.pasta.id = :pastaId
            AND doc.valor = :valor
            AND doc.user = :user
        """)
    Optional<DocumentoEntity> findEqual(long contatoId, TipoDocumento tipoDoc, LocalDate dataEmissao, long pastaId,
            double valor, UserEntity user);

    @Query("""
        SELECT doc, pasta, emissor, conta, banco
        FROM DocumentoEntity doc
        LEFT JOIN PastaEntity pasta ON pasta.id = doc.pasta.id
        LEFT JOIN ContatoEntity emissor ON emissor.id = doc.emissor.id
        LEFT JOIN ContaEntity conta ON conta.id = pasta.conta.id
        LEFT JOIN BancoEntity banco ON banco.id = conta.banco.id
        WHERE doc.emissor.id = :contatoId AND doc.numDoc = :numDoc
        """)
    Optional<DocumentoEntity> findNotaEqual(long contatoId, long numDoc, UserEntity user);

}
