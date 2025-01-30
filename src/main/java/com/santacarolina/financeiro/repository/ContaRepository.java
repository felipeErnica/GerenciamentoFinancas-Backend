package com.santacarolina.financeiro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.ContaEntity;
import com.santacarolina.financeiro.entity.UserEntity;

/**
 * ContaRepository
 */
@Repository
public interface ContaRepository extends JpaRepository<ContaEntity, Long> {

    @Query("""
        SELECT conta, banco 
        FROM ContaEntity conta
        LEFT JOIN BancoEntity banco ON banco.id = conta.banco.id
        ORDER BY conta.nomeConta
        WHERE conta.user = :user
        """)
    List<ContaEntity> findByUser(UserEntity user);


    @Override
    @Query("""
        SELECT conta, banco 
        FROM ContaEntity conta
        LEFT JOIN BancoEntity banco ON banco.id = conta.banco.id
        WHERE conta.id = :id
        """)
    Optional<ContaEntity> findById(Long id);

    @Query("""
        SELECT conta, banco 
        FROM ContaEntity conta
        LEFT JOIN BancoEntity banco ON banco.id = conta.banco.id
        WHERE conta.agencia = :agencia AND conta.numeroConta = :numeroConta AND conta.banco.id = :bancoId AND conta.user = :user
        """)
    Optional<ContaEntity> findEqual(@Param("agencia") String agencia, 
            @Param("numeroConta") String numeroConta, 
            @Param("bancoId") long bancoId,
            @Param("user") UserEntity user);


    @Query("""
        SELECT conta, banco 
        FROM ContaEntity conta
        LEFT JOIN BancoEntity banco ON banco.id = conta.banco.id
        WHERE conta.nomeConta = :apelido AND conta.user = :user
        """)
    Optional<ContaEntity> findByApelido(String apelido, UserEntity user);

}
