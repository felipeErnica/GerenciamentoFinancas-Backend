package com.santacarolina.financeiro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.ContaEntity;

/**
 * ContaRepository
 */
@Repository
public interface ContaRepository extends JpaRepository<ContaEntity, Long> {

    @Override
    @Query("""
        SELECT conta, banco 
        FROM ContaEntity conta
        LEFT JOIN BancoEntity banco ON banco.id = conta.banco.id
        ORDER BY conta.nomeConta
        """)
    List<ContaEntity> findAll();


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
        WHERE conta.agencia = :agencia AND conta.numeroConta = :numeroConta AND conta.banco.id = :bancoId
        """)
    Optional<ContaEntity> findEqual(@Param("agencia") String agencia, 
            @Param("numeroConta") String numeroConta, 
            @Param("bancoId") long bancoId);


    @Query("""
        SELECT conta, banco 
        FROM ContaEntity conta
        LEFT JOIN BancoEntity banco ON banco.id = conta.banco.id
        WHERE conta.nomeConta = :apelido
        """)
    Optional<ContaEntity> findByApelido(String apelido);

}
