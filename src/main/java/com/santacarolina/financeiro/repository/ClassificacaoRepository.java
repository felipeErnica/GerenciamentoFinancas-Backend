package com.santacarolina.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santacarolina.financeiro.entity.ClassificacaoEntity;

/**
 * ClassificacaoRepository
 */
public interface ClassificacaoRepository extends JpaRepository<ClassificacaoEntity, Long> {
}
