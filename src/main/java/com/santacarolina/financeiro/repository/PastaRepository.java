package com.santacarolina.financeiro.repository;

import com.santacarolina.financeiro.models.entities.PastaContabil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PastaRepository extends JpaRepository<PastaContabil,Long> {
}
