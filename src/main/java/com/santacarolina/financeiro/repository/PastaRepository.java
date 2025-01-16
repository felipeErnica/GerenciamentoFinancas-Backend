package com.santacarolina.financeiro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.PastaEntity;

/**
 * PastaRepository
 */
@Repository
public interface PastaRepository extends JpaRepository<PastaEntity, Long> {
    Optional<PastaEntity> findByNome(String nome);
}
