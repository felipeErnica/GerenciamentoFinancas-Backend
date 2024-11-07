package com.santacarolina.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santacarolina.financeiro.entity.ExtratoEntity;

/**
 * ExtratoRepository
 */
public interface ExtratoRepository extends JpaRepository<ExtratoEntity, Long> {
}
