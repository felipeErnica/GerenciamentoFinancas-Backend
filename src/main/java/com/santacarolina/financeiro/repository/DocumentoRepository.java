package com.santacarolina.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.DocumentoEntity;

/**
 * DocumentoRepository
 */
@Repository
public interface DocumentoRepository extends JpaRepository<DocumentoEntity, Long> {
}
