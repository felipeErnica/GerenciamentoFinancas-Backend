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
        ORDER BY class.numeroIdentificacao
        """)
    List<ClassificacaoEntity> findAll();
    
    @Override
    @Query("""
        SELECT class, cat
        FROM ClassificacaoEntity class
        LEFT JOIN CategoriaEntity cat ON cat.id = class.categoria.id
        WHERE class.id = :id
        """)
    Optional<ClassificacaoEntity> findById(Long id);
    
    @Query("""
        SELECT class, cat
        FROM ClassificacaoEntity class
        LEFT JOIN CategoriaEntity cat ON cat.id = class.categoria.id
        WHERE class.numeroIdentificacao = :numeroIdentificacao
        """)
    Optional<ClassificacaoEntity> findByNumeroIdentificacao(String numeroIdentificacao);
    
    @Query("""
        SELECT class, cat
        FROM ClassificacaoEntity class
        LEFT JOIN CategoriaEntity cat ON cat.id = class.categoria.id
        WHERE class.nomeClassificacao = :nomeClassificacao
        """)
    Optional<ClassificacaoEntity> findByNomeClassificacao(String nomeClassificacao);

}
