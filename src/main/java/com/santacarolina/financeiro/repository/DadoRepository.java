package com.santacarolina.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.DadoEntity;

@Repository
public interface DadoRepository extends JpaRepository<DadoEntity, Long> {
}
