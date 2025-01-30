package com.santacarolina.financeiro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.PastaEntity;
import com.santacarolina.financeiro.entity.UserEntity;

/**
 * PastaRepository
 */
@Repository
public interface PastaRepository extends JpaRepository<PastaEntity, Long> {

    @Query("""
        SELECT pasta, conta, banco
        FROM PastaEntity pasta
        LEFT JOIN ContaEntity conta ON conta.id = pasta.conta.id
        LEFT JOIN BancoEntity banco ON banco.id = conta.banco.id
        WHERE pasta.user = :user
        ORDER BY pasta.nome
        """)
    List<PastaEntity> findByUser(UserEntity user);

    @Query("""
        SELECT pasta, conta, banco
        FROM PastaEntity pasta
        LEFT JOIN ContaEntity conta ON conta.id = pasta.conta.id
        LEFT JOIN BancoEntity banco ON banco.id = conta.banco.id
        WHERE pasta.nome = :nome AND pasta.user = :user
        """)
    Optional<PastaEntity> findByNome(String nome, UserEntity user);
    
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
