package com.santacarolina.financeiro.repository;

import com.santacarolina.financeiro.models.entities.ChavePix;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PixRepository extends JpaRepository<ChavePix, Long> {
    Optional<ChavePix> findByChave(String chave);
}
