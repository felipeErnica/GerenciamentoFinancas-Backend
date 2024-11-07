package com.santacarolina.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santacarolina.financeiro.entity.CategoriaEntity;

/**
 * CategoriaRepository
 */
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
}
