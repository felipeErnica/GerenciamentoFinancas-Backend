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

    @Query(value = """
        SELECT class.*, cat.*
        FROM classificacoes_contabeis class
        LEFT JOIN categorias_contabeis cat ON cat.id = class.categoria_id
        ORDER BY class.numero_identificacao;
        """, nativeQuery = true)
    List<ClassificacaoEntity> findAll();
    
    Optional<ClassificacaoEntity> findById(long id);
    
    Optional<ClassificacaoEntity> findByNumeroIdentificacao(String numeroIdentificacao);
    
    Optional<ClassificacaoEntity> findByNomeClassificacao(String nomeClassificacao);
}
