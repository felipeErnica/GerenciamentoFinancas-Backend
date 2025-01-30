package com.santacarolina.financeiro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.DadoEntity;
import com.santacarolina.financeiro.entity.UserEntity;

@Repository
public interface DadoRepository extends JpaRepository<DadoEntity, Long> {

    @Query("""
        SELECT dado, banco, contato
        FROM DadoEntity dado
        LEFT JOIN BancoEntity banco ON banco.id = dado.banco.id
        LEFT JOIN ContatoEntity contato ON contato.id = dado.contato.id
        ORDER BY contato.nome
        WHERE dado.user = :user
        """)
    List<DadoEntity> findByUser(UserEntity user);

    @Query("""
        SELECT dado, banco, contato
        FROM DadoEntity dado
        LEFT JOIN BancoEntity banco ON banco.id = dado.banco.id
        LEFT JOIN ContatoEntity contato ON contato.id = dado.contato.id
        WHERE dado.contato.id = :contatoId
        """)
    List<DadoEntity> findByContato(long contatoId);

    @Query("""
        SELECT dado, banco, contato
        FROM DadoEntity dado
        LEFT JOIN BancoEntity banco ON banco.id = dado.banco.id
        LEFT JOIN ContatoEntity contato ON contato.id = dado.contato.id
        WHERE dado.agencia = :agencia AND dado.numeroConta = :numeroConta AND dado.banco.id = :bancoId AND dado.user = :user
        """)
    Optional<DadoEntity> findEqual(String agencia, String numeroConta, long bancoId, UserEntity user);

    @Override
    @Query("""
        SELECT dado, banco, contato
        FROM DadoEntity dado
        LEFT JOIN BancoEntity banco ON banco.id = dado.banco.id
        LEFT JOIN ContatoEntity contato ON contato.id = dado.contato.id
        WHERE dado.id = :id
        """)
    Optional<DadoEntity> findById(Long id);

}
