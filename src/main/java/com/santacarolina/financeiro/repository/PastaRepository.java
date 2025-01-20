package com.santacarolina.financeiro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.PastaEntity;

/**
 * PastaRepository
 */
@Repository
public interface PastaRepository extends JpaRepository<PastaEntity, Long> {

    @Override
    @Query("""
        SELECT pasta, conta, banco
        FROM PastaEntity pasta
        LEFT JOIN ContaEntity conta ON conta.id = pasta.conta.id
        LEFT JOIN BancoEntity banco ON banco.id = conta.banco.id
        """)
    List<PastaEntity> findAll();

    @Query("""
        SELECT pasta, conta, banco
        FROM PastaEntity pasta
        LEFT JOIN ContaEntity conta ON conta.id = pasta.conta.id
        LEFT JOIN BancoEntity banco ON banco.id = conta.banco.id
        WHERE pasta.nome = :nome
        """)
    Optional<PastaEntity> findByNome(String nome);
    
    @Override
    @Query("""
        SELECT pasta, conta, banco
        FROM PastaEntity pasta
        LEFT JOIN ContaEntity conta ON conta.id = pasta.conta.id
        LEFT JOIN BancoEntity banco ON banco.id = conta.banco.id
        WHERE pasta.id = :id
        """)
    Optional<PastaEntity> findById(Long id);
}
