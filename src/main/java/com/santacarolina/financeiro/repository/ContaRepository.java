package com.santacarolina.financeiro.repository;

import com.santacarolina.financeiro.models.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<ContaBancaria, Long> {

    @Query("SELECT c FROM ContaBancaria c WHERE c.agencia = :agencia AND c.numeroConta = :numeroConta AND c.banco.id = :idBanco")
    Optional<ContaBancaria> getEqual(String agencia, String numeroConta, long idBanco);

}
