package com.santacarolina.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.ConciliacaoEntity;

/**
 * ConciliacaoRepository
 */
@Repository
public interface ConciliacaoRepository extends JpaRepository<ConciliacaoEntity, Long>{
}
