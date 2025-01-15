package com.santacarolina.financeiro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.BancoEntity;

/**
 * BancoRepository
 */
@Repository
public interface BancoRepository extends JpaRepository<BancoEntity, Long> {
    Optional<BancoEntity> findByNomeBanco(String nomeBanco);
    Optional<BancoEntity> findByApelidoBanco(String apelido);
}
