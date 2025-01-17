package com.santacarolina.financeiro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.ClassificacaoEntity;

/**
 * ClassificacaoRepository
 */
@Repository
public interface ClassificacaoRepository extends JpaRepository<ClassificacaoEntity, Long> {

    @Override
    @Query("""
        SELECT class, cat
        FROM ClassificacaoEntity class
        LEFT JOIN CategoriaEntity cat ON cat.id = class.categoria.id
        """)
    List<ClassificacaoEntity> findAll();
    
    //Optional<ClassificacaoEntity> findById(long id);
    
    Optional<ClassificacaoEntity> findByNumeroIdentificacao(String numeroIdentificacao);
    
    Optional<ClassificacaoEntity> findByNomeClassificacao(String nomeClassificacao);
}
