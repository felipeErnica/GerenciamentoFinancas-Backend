package com.santacarolina.financeiro.repository;

import com.santacarolina.financeiro.models.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContatoRepository extends JpaRepository<Contato,Long> {

    @Query("SELECT c FROM Contato c WHERE c.cnpj LIKE :cnpj")
    Optional<Contato> findByCnpj(String cnpj);

}
