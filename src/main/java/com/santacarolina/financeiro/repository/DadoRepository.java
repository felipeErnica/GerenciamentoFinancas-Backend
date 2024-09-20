package com.santacarolina.financeiro.repository;

import com.santacarolina.financeiro.models.DadoBancario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DadoRepository extends JpaRepository<DadoBancario, Long> {

    @Query("SELECT d FROM DadoBancario d WHERE d.agencia = :agencia AND d.numeroConta = :numeroConta AND d.banco.id = :idBanco")
    Optional<DadoBancario> findEqual(String agencia, String numeroConta, long idBanco);

}
