package com.santacarolina.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.BancoEntity;

/**
 * BancoRepository
 */
@Repository
public interface BancoRepository extends JpaRepository<BancoEntity, Long> {
}
