package com.santacarolina.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santacarolina.financeiro.entity.DocumentoEntity;

/**
 * DocumentoRepository
 */
public interface DocumentoRepository extends JpaRepository<DocumentoEntity, Long> {
}
