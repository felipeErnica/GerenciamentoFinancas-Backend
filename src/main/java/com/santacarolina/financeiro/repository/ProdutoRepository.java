package com.santacarolina.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.ProdutoEntity;

/**
 * ProdutoRepository
 */
@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
}
