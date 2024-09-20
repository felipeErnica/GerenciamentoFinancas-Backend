package com.santacarolina.financeiro.repository;

import com.santacarolina.financeiro.models.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long> {
    Optional<Banco> findByNomeBanco(String nomeBanco);
}
