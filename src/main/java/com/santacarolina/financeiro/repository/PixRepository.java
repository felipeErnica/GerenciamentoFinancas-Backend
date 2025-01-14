package com.santacarolina.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.PixEntity;

/**
 * PixRepository
 */
@Repository
public interface PixRepository extends JpaRepository<PixEntity, Long> {
}
