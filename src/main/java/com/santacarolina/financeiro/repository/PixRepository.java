package com.santacarolina.financeiro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.PixEntity;

/**
 * PixRepository
 */
@Repository
public interface PixRepository extends JpaRepository<PixEntity, Long> {
    Optional<PixEntity> findByChave(String chave);

    @Query("""
        SELECT p
        FROM PixEntity p
        WHERE p.contato.id = :contatoId
        """)
    List<PixEntity> findByContato(long contatoId);

    @Query("""
        SELECT p
        FROM PixEntity p
        WHERE p.dado.id = :dadoId
        """)
    Optional<PixEntity> findByDado(long dadoId);
}
