package com.santacarolina.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.DuplicataEntity;

/**
 * DuplicataRepository
 */
@Repository
public interface DuplicataRepository extends JpaRepository<DuplicataEntity, Long> {
}
