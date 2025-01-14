package com.santacarolina.financeiro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santacarolina.financeiro.entity.ClassificacaoEntity;

/**
 * ClassificacaoRepository
 */
public interface ClassificacaoRepository extends JpaRepository<ClassificacaoEntity, Long> {
    Optional<ClassificacaoEntity> findByNumeroIdentificacao(String numeroIdentificacao);
    Optional<ClassificacaoEntity> findByNomeClassificacao(String nomeClassificacao);
}
