package com.santacarolina.financeiro.repository;

import com.santacarolina.financeiro.models.entities.DadoBancario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DadoRepository extends JpaRepository<DadoBancario, Long> {
}
