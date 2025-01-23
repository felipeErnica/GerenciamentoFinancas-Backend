package com.santacarolina.financeiro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.CategoriaEntity;

/**
 * CategoriaRepository
 */
@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

    @Override
    @Query("SELECT pasta FROM PastaEntity pasta ORDER BY pasta.numeroCategoria")
    List<CategoriaEntity> findAll();

    Optional<CategoriaEntity> findByNome(String nome);
    Optional<CategoriaEntity> findByNumeroCategoria(String numeroCategoria);
}
