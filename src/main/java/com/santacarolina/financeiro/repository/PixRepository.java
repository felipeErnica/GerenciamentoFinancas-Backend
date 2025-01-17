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

    @Override
    @Query("""
        SELECT p, c, d, b
        FROM PixEntity p
        LEFT JOIN ContatoEntity c ON c.id = p.contato.id
        LEFT JOIN DadoEntity d ON d.id = p.dado.id
        LEFT JOIN BancoEntity b ON b.id = d.banco.id
        ORDER BY c.nome
        """)
    List<PixEntity> findAll();

    @Override
    @Query("""
        SELECT p, c, d, b
        FROM PixEntity p
        LEFT JOIN ContatoEntity c ON c.id = p.contato.id
        LEFT JOIN DadoEntity d ON d.id = p.dado.id
        LEFT JOIN BancoEntity b ON b.id = d.banco.id
        WHERE p.id = :id
        """)
    Optional<PixEntity> findById(Long id);

    @Query("""
        SELECT p, c, d, b
        FROM PixEntity p
        LEFT JOIN ContatoEntity c ON c.id = p.contato.id
        LEFT JOIN DadoEntity d ON d.id = p.dado.id
        LEFT JOIN BancoEntity b ON b.id = d.banco.id
        WHERE p.chave = :chave
        """)
    Optional<PixEntity> findByChave(String chave);

    @Query("""
        SELECT p, c, d, b
        FROM PixEntity p
        LEFT JOIN ContatoEntity c ON c.id = p.contato.id
        LEFT JOIN DadoEntity d ON d.id = p.dado.id
        LEFT JOIN BancoEntity b ON b.id = d.banco.id
        WHERE p.contato.id = :contatoId
        """)
    List<PixEntity> findByContato(long contatoId);

    @Query("""
        SELECT p, c, d, b
        FROM PixEntity p
        LEFT JOIN ContatoEntity c ON c.id = p.contato.id
        LEFT JOIN DadoEntity d ON d.id = p.dado.id
        LEFT JOIN BancoEntity b ON b.id = d.banco.id
        WHERE p.dado.id = :dadoId
        """)
    Optional<PixEntity> findByDado(long dadoId);
}
