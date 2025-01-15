package com.santacarolina.financeiro.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.dto.ContaDTO;
import com.santacarolina.financeiro.entity.ContaEntity;

/**
 * ContaRepository
 */
@Repository
public interface ContaRepository extends JpaRepository<ContaEntity, Long> {

    @Query("""
        SELECT c 
        FROM ContaEntity 
            WHERE c.agencia = :agencia AND c.numeroConta = :agencia AND c.banco.id = :bancoId; 
        """)
    Optional<ContaDTO> findEqual(String agencia, String numeroConta, long bancoId);
}
