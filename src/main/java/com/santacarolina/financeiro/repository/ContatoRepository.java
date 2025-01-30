package com.santacarolina.financeiro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.ContatoEntity;
import com.santacarolina.financeiro.entity.UserEntity;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoEntity, Long> {
    Optional<ContatoEntity> findByCpfAndUser(String cpf, UserEntity user);
    Optional<ContatoEntity> findByCnpjAndUser(String cnpj, UserEntity user);
    Optional<ContatoEntity> findByIeAndUser(String ie, UserEntity user);
    Optional<ContatoEntity> findByNomeAndUser(String nome, UserEntity user);
    List<ContatoEntity> findByUser(UserEntity user);
}
