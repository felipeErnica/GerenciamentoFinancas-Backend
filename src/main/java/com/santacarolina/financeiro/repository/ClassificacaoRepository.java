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
    @Query(value = """
        SELECT 
            class.id, class.categoria_id, class.fluxo_caixa, class.nome_classificacao, class.numero_identificacao, 
            cat.nome, cat.numero_categoria
        FROM classificacoes_contabeis class
        LEFT JOIN categorias_contabeis cat ON cat.id = class.categoria_id
        """, nativeQuery = true)
    List<ClassificacaoEntity> findAll();
    
    //Optional<ClassificacaoEntity> findById(long id);
    
    Optional<ClassificacaoEntity> findByNumeroIdentificacao(String numeroIdentificacao);
    
    Optional<ClassificacaoEntity> findByNomeClassificacao(String nomeClassificacao);
}
