package com.santacarolina.financeiro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.PixEntity;
import com.santacarolina.financeiro.entity.UserEntity;

/**
 * PixRepository
 */
@Repository
public interface PixRepository extends JpaRepository<PixEntity, Long> {

    @Query("""
        SELECT pix, contato, dado, banco
        FROM PixEntity pix
        LEFT JOIN ContatoEntity contato ON contato.id = pix.contato.id
        LEFT JOIN DadoEntity dado ON dado.id = pix.dado.id
        LEFT JOIN BancoEntity banco ON banco.id = dado.banco.id
        WHERE pix.user = :user
        ORDER BY contato.nome
        """)
    List<PixEntity> findByUser(UserEntity user);

    @Override
    @Query("""
        SELECT pix, contato, dado, banco
        FROM PixEntity pix
        LEFT JOIN ContatoEntity contato ON contato.id = pix.contato.id
        LEFT JOIN DadoEntity dado ON dado.id = pix.dado.id
        LEFT JOIN BancoEntity banco ON banco.id = dado.banco.id
        WHERE pix.id = :id
        """)
    Optional<PixEntity> findById(Long id);

    @Query("""
        SELECT pix, contato, dado, banco
        FROM PixEntity pix
        LEFT JOIN ContatoEntity contato ON contato.id = pix.contato.id
        LEFT JOIN DadoEntity dado ON dado.id = pix.dado.id
        LEFT JOIN BancoEntity banco ON banco.id = dado.banco.id
        WHERE pix.chave = :chave AND pix.user = :user
        """)
    Optional<PixEntity> findByChave(String chave, UserEntity user);

    @Query("""
        SELECT pix, contato, dado, banco
        FROM PixEntity pix
        LEFT JOIN ContatoEntity contato ON contato.id = pix.contato.id
        LEFT JOIN DadoEntity dado ON dado.id = pix.dado.id
        LEFT JOIN BancoEntity banco ON banco.id = dado.banco.id
        WHERE pix.contato.id = :contatoId
        """)
    List<PixEntity> findByContato(long contatoId);

    @Query("""
        SELECT pix, contato, dado, banco
        FROM PixEntity pix
        LEFT JOIN ContatoEntity contato ON contato.id = pix.contato.id
        LEFT JOIN DadoEntity dado ON dado.id = pix.dado.id
        LEFT JOIN BancoEntity banco ON banco.id = dado.banco.id
        WHERE pix.dado.id = :dadoId
        """)
    Optional<PixEntity> findByDado(long dadoId);
}
