package com.santacarolina.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santacarolina.financeiro.entity.ProdutoEntity;

/**
 * ProdutoRepository
 */
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
}
