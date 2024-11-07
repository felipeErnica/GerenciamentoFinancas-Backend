package com.santacarolina.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santacarolina.financeiro.entity.ContatoEntity;

public interface ContatoRepository extends JpaRepository<ContatoEntity, Long> {
}

