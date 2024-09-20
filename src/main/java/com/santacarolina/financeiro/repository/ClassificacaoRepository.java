package com.santacarolina.financeiro.repository;

import com.santacarolina.financeiro.models.ClassificacaoContabil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificacaoRepository extends JpaRepository<ClassificacaoContabil,Long> {
    @Query("SELECT c FROM ClassificacaoContabil c WHERE c.numeroIdentificacao = :numeroIdentificacao")
    ClassificacaoContabil getByNumero(long numeroIdentificacao);
}
