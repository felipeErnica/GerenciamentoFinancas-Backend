package com.santacarolina.financeiro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.DadoEntity;

@Repository
public interface DadoRepository extends JpaRepository<DadoEntity, Long> {

    @Query("""
        SELECT d
        FROM DadoEntity d
        WHERE d.contato.id = :contatoId
        """)
    List<DadoEntity> findByContato(long contatoId);

    @Query("""
        SELECT d
        FROM DadoEntity d
        WHERE d.agencia = :agencia AND d.numeroConta = :numeroConta AND d.banco.id = :bancoId
        """)
    Optional<DadoEntity> findEqual(String agencia, String numeroConta, long bancoId);
}
