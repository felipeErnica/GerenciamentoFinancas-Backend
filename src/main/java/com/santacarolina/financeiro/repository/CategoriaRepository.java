package com.santacarolina.financeiro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santacarolina.financeiro.dto.CategoriaDTO;
import com.santacarolina.financeiro.entity.CategoriaEntity;

/**
 * CategoriaRepository
 */
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
    Optional<CategoriaDTO> findByNome(String nome);
}
