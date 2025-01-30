package com.santacarolina.financeiro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.ClassificacaoEntity;
import com.santacarolina.financeiro.entity.UserEntity;

/**
 * ClassificacaoRepository
 */
@Repository
public interface ClassificacaoRepository extends JpaRepository<ClassificacaoEntity, Long> {

    @Query("""
        SELECT class, cat
        FROM ClassificacaoEntity class
        LEFT JOIN CategoriaEntity cat ON cat.id = class.categoria.id
        WHERE class.user = :user
        ORDER BY cat.numeroCategoria, class.numeroIdentificacao
        """)
    List<ClassificacaoEntity> findByUser(UserEntity user);
    
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
        WHERE class.numeroIdentificacao = :numeroIdentificacao AND class.user = :user
        """)
    Optional<ClassificacaoEntity> findByNumeroIdentificacao(String numeroIdentificacao, UserEntity user);
    
    @Query("""
        SELECT class, cat
        FROM ClassificacaoEntity class
        LEFT JOIN CategoriaEntity cat ON cat.id = class.categoria.id
        WHERE class.nomeClassificacao = :nomeClassificacao AND class.user = :user
        """)
    Optional<ClassificacaoEntity> findByNomeClassificacao(String nomeClassificacao, UserEntity user);

}
