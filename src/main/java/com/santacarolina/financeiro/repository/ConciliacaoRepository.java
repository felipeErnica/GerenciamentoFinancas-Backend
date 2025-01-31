package com.santacarolina.financeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.ConciliacaoEntity;
import com.santacarolina.financeiro.entity.UserEntity;

/**
 * ConciliacaoRepository
 */
@Repository
public interface ConciliacaoRepository extends JpaRepository<ConciliacaoEntity, Long>{
    
    @Query("""
        SELECT conciliacao, duplicata, extrato
        FROM ConciliacaoEntity conciliacao
        LEFT JOIN ExtratoEntity extrato ON conciliacao.extrato.id = extrato.id
        LEFT JOIN DuplicataEntity duplicata ON duplicata.id = conciliacao.duplicata.id
        WHERE conciliacao.user = :user
        ORDER BY extrato.dataTransacao DESC
        """)
    List<ConciliacaoEntity> findByUser(UserEntity user);

    @Query("""
        SELECT conciliacao, duplicata, extrato
        FROM ConciliacaoEntity conciliacao
        LEFT JOIN ExtratoEntity extrato ON conciliacao.extrato.id = extrato.id
        LEFT JOIN DuplicataEntity duplicata ON duplicata.id = conciliacao.duplicata.id
        WHERE conciliacao.extrato.id = :extratoId
        ORDER BY extrato.dataTransacao DESC
        """)
    List<ConciliacaoEntity> findByExtrato(long extratoId);

    @Query("""
        SELECT conciliacao, duplicata, extrato
        FROM ConciliacaoEntity conciliacao
        LEFT JOIN ExtratoEntity extrato ON conciliacao.extrato.id = extrato.id
        LEFT JOIN DuplicataEntity duplicata ON duplicata.id = conciliacao.duplicata.id
        WHERE conciliacao.duplicata.id = :duplicataId
        ORDER BY extrato.dataTransacao DESC
        """)
    List<ConciliacaoEntity> findByDuplicata(long duplicataId);


}
