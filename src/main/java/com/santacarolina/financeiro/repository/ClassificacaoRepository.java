package com.santacarolina.financeiro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.ClassificacaoEntity;

/**
 * ClassificacaoRepository
 */
@Repository
public interface ClassificacaoRepository extends JpaRepository<ClassificacaoEntity, Long> {
    Optional<ClassificacaoEntity> findByNumeroIdentificacao(String numeroIdentificacao);
    Optional<ClassificacaoEntity> findByNomeClassificacao(String nomeClassificacao);
}
