package com.santacarolina.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.ContaEntity;

/**
 * ContaRepository
 */
@Repository
public interface ContaRepository extends JpaRepository<ContaEntity, Long> {
}
