package com.santacarolina.financeiro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.DuplicataEntity;
import com.santacarolina.financeiro.entity.UserEntity;

/**
 * DuplicataRepository
 */
@Repository
public interface DuplicataRepository extends JpaRepository<DuplicataEntity, Long> {
    
    @Query("""
        SELECT dup, doc, dado, pix, emissor, pasta, conta, banco
        FROM DuplicataEntity dup
        LEFT JOIN DocumentoEntity doc ON doc.id = dup.documento.id
        LEFT JOIN DadoEntity dado ON dado.id = dup.dado.id
        LEFT JOIN PixEntity pix ON pix.id = dup.pix.id
        LEFT JOIN ContatoEntity emissor ON emissor.id = doc.emissor.id
        LEFT JOIN PastaEntity pasta ON pasta.id = doc.pasta.id
        LEFT JOIN ContaEntity conta ON conta.id = pasta.conta.id
        LEFT JOIN BancoEntity banco ON banco.id = conta.banco.id 
        WHERE dup.user = :user
        ORDER BY dup.dataVencimento DESC
        """) 
    List<DuplicataEntity> findByUser(UserEntity user);

    @Override
    @Query("""
        SELECT dup, doc, dado, pix, emissor, pasta, conta, banco
        FROM DuplicataEntity dup
        LEFT JOIN DocumentoEntity doc ON doc.id = dup.documento.id
        LEFT JOIN DadoEntity dado ON dado.id = dup.dado.id
        LEFT JOIN PixEntity pix ON pix.id = dup.pix.id
        LEFT JOIN ContatoEntity emissor ON emissor.id = doc.emissor.id
        LEFT JOIN PastaEntity pasta ON pasta.id = doc.pasta.id
        LEFT JOIN ContaEntity conta ON conta.id = pasta.conta.id
        LEFT JOIN BancoEntity banco ON banco.id = conta.banco.id 
        WHERE dup.id = :id
        """) 
    Optional<DuplicataEntity> findById(Long id);

    @Query("""
        SELECT dup, doc, dado, pix, emissor, pasta, conta, banco
        FROM DuplicataEntity dup
        LEFT JOIN DocumentoEntity doc ON doc.id = dup.documento.id
        LEFT JOIN DadoEntity dado ON dado.id = dup.dado.id
        LEFT JOIN PixEntity pix ON pix.id = dup.pix.id
        LEFT JOIN ContatoEntity emissor ON emissor.id = doc.emissor.id
        LEFT JOIN PastaEntity pasta ON pasta.id = doc.pasta.id
        LEFT JOIN ContaEntity conta ON conta.id = pasta.conta.id
        LEFT JOIN BancoEntity banco ON banco.id = conta.banco.id 
        WHERE dup.paga = true AND dup.user = :user
        ORDER BY dup.dataVencimento DESC
        """) 
    List<DuplicataEntity> findPagas(UserEntity user);

    @Query("""
        SELECT dup, doc, dado, pix, emissor, pasta, conta, banco
        FROM DuplicataEntity dup
        LEFT JOIN DocumentoEntity doc ON doc.id = dup.documento.id
        LEFT JOIN DadoEntity dado ON dado.id = dup.dado.id
        LEFT JOIN PixEntity pix ON pix.id = dup.pix.id
        LEFT JOIN ContatoEntity emissor ON emissor.id = doc.emissor.id
        LEFT JOIN PastaEntity pasta ON pasta.id = doc.pasta.id
        LEFT JOIN ContaEntity conta ON conta.id = pasta.conta.id
        LEFT JOIN BancoEntity banco ON banco.id = conta.banco.id 
        WHERE dup.paga = false AND dup.user = :user
        ORDER BY dup.dataVencimento 
        """) 
    List<DuplicataEntity> findNaoPagas(UserEntity user);

    @Query("""
        SELECT dup, doc, dado, pix, emissor, pasta, conta, banco
        FROM DuplicataEntity dup
        LEFT JOIN DocumentoEntity doc ON doc.id = dup.documento.id
        LEFT JOIN DadoEntity dado ON dado.id = dup.dado.id
        LEFT JOIN PixEntity pix ON pix.id = dup.pix.id
        LEFT JOIN ContatoEntity emissor ON emissor.id = doc.emissor.id
        LEFT JOIN PastaEntity pasta ON pasta.id = doc.pasta.id
        LEFT JOIN ContaEntity conta ON conta.id = pasta.conta.id
        LEFT JOIN BancoEntity banco ON banco.id = conta.banco.id 
        WHERE dup.documento.id = :documentoId
        ORDER BY dup.dataVencimento
        """)
    List<DuplicataEntity> findByDoc(long documentoId);

}
