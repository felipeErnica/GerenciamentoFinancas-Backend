package com.santacarolina.financeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.ExtratoEntity;
import com.santacarolina.financeiro.entity.UserEntity;

/**
 * ExtratoRepository
 */
@Repository
public interface ExtratoRepository extends JpaRepository<ExtratoEntity, Long> {

    @Query("""
        SELECT extrato, conta, banco
        FROM ExtratoEntity extrato
        LEFT JOIN ContaEntity conta ON conta.id = extrato.conta.id
        LEFT JOIN BancoEntity banco ON banco.id = conta.banco.id
        WHERE extrato.conta.id = :contaId
        ORDER BY extrato.dataTransacao DESC
    """)
    List<ExtratoEntity> findByContaId(long contaId);

    @Query("""
        SELECT extrato, conta, banco
        FROM ExtratoEntity extrato
        LEFT JOIN ContaEntity conta ON conta.id = extrato.conta.id
        LEFT JOIN BancoEntity banco ON banco.id = conta.banco.id
        WHERE extrato.conciliado = :isConciliado AND extrato.user = :user
        ORDER BY extrato.dataTransacao DESC
    """)
    List<ExtratoEntity> findByConciliado(boolean isConciliado, UserEntity user);
}
