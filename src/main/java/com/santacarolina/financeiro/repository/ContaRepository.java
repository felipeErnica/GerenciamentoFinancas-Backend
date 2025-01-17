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
        SELECT c, b 
        FROM ContaEntity c
        LEFT JOIN BancoEntity b ON b.id = c.banco.id
        ORDER BY c.nomeConta
        """)
    List<ContaEntity> findAll();


    @Override
    @Query("""
        SELECT c, b 
        FROM ContaEntity c
        LEFT JOIN BancoEntity b ON b.id = c.banco.id
        WHERE c.id = :id
        """)
    Optional<ContaEntity> findById(Long id);

    @Query("""
        SELECT c, b 
        FROM ContaEntity c
        LEFT JOIN BancoEntity b ON b.id = c.banco.id
        WHERE c.agencia = :agencia AND c.numeroConta = :numeroConta AND c.banco.id = :bancoId
        """)
    Optional<ContaEntity> findEqual(@Param("agencia") String agencia, 
            @Param("numeroConta") String numeroConta, 
            @Param("bancoId") long bancoId);
}
