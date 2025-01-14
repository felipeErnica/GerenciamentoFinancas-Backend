package com.santacarolina.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.ExtratoEntity;

/**
 * ExtratoRepository
 */
@Repository
public interface ExtratoRepository extends JpaRepository<ExtratoEntity, Long> {
}
