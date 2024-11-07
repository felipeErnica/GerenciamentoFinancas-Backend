package com.santacarolina.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santacarolina.financeiro.entity.PixEntity;

/**
 * PixRepository
 */
public interface PixRepository extends JpaRepository<PixEntity, Long> {
}
