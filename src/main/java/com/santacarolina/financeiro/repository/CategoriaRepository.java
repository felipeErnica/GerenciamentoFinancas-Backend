package com.santacarolina.financeiro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.CategoriaEntity;

/**
 * CategoriaRepository
 */
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
    Optional<CategoriaEntity> findByNome(String nome);
    Optional<CategoriaEntity> findByNumeroCategoria(String numeroCategoria);
}
