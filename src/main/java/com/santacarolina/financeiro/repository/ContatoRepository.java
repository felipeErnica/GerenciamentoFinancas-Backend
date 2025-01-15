package com.santacarolina.financeiro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.ContatoEntity;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoEntity, Long> {
    Optional<ContatoEntity> findByCpf(String cpf);
    Optional<ContatoEntity> findByCnpj(String cnpj);
    Optional<ContatoEntity> findByIe(String ie);
    Optional<ContatoEntity> findByNome(String nome);
}
