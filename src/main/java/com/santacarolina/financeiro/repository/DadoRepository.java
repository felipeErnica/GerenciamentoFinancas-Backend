package com.santacarolina.financeiro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.DadoEntity;

@Repository
public interface DadoRepository extends JpaRepository<DadoEntity, Long> {

    @Override
    @Query("""
        SELECT d, b, c
        FROM DadoEntity d
        LEFT JOIN BancoEntity b ON b.id = d.banco.id
        LEFT JOIN ContatoEntity c ON c.id = d.contato.id
        ORDER BY c.nome
        """)
    List<DadoEntity> findAll();

    @Query("""
        SELECT d, b, c
        FROM DadoEntity d
        LEFT JOIN BancoEntity b ON b.id = d.banco.id
        LEFT JOIN ContatoEntity c ON c.id = d.contato.id
        WHERE d.contato.id = :contatoId
        """)
    List<DadoEntity> findByContato(long contatoId);

    @Query("""
        SELECT d, b, c
        FROM DadoEntity d
        LEFT JOIN BancoEntity b ON b.id = d.banco.id
        LEFT JOIN ContatoEntity c ON c.id = d.contato.id
        WHERE d.agencia = :agencia AND d.numeroConta = :numeroConta AND d.banco.id = :bancoId
        """)
    Optional<DadoEntity> findEqual(String agencia, String numeroConta, long bancoId);

    @Override
    @Query("""
        SELECT d, b, c
        FROM DadoEntity d
        LEFT JOIN BancoEntity b ON b.id = d.banco.id
        LEFT JOIN ContatoEntity c ON c.id = d.contato.id
        WHERE d.id = :id
        """)
    Optional<DadoEntity> findById(Long id);

}
