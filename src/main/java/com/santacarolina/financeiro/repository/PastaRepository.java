package com.santacarolina.financeiro.repository;

import com.santacarolina.financeiro.models.PastaContabil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PastaRepository extends JpaRepository<PastaContabil,Long> {
    Optional<PastaContabil> findByNomeIgnoreCase(String nome);
}
