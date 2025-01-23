package com.santacarolina.financeiro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.CategoriaEntity;

/**
 * CategoriaRepository
 */
@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

    @Override
    @Query("SELECT categoria FROM CategoriaEntity categoria ORDER BY categoria.numeroCategoria")
    List<CategoriaEntity> findAll();

    Optional<CategoriaEntity> findByNome(String nome);
    Optional<CategoriaEntity> findByNumeroCategoria(String numeroCategoria);
}
