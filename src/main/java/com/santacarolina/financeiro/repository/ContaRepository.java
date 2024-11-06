package com.santacarolina.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santacarolina.financeiro.entity.ContaEntity;

/**
 * ContaRepository
 */
public interface ContaRepository extends JpaRepository<ContaEntity, Long> {
}
