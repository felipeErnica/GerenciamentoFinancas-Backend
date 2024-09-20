package com.santacarolina.financeiro.repository;

import com.santacarolina.financeiro.models.ChavePix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PixRepository extends JpaRepository<ChavePix, Long> {
    Optional<ChavePix> findByChave(String chave);
}
