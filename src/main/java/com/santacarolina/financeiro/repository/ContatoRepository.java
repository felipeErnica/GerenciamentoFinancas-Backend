package com.santacarolina.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.ContatoEntity;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoEntity, Long> {
}
