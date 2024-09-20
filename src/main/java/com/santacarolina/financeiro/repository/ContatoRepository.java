package com.santacarolina.financeiro.repository;

import com.santacarolina.financeiro.models.ChavePix;
import com.santacarolina.financeiro.models.Contato;
import com.santacarolina.financeiro.models.DadoBancario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContatoRepository extends JpaRepository<Contato,Long> {
    @Query("SELECT c FROM Contato c WHERE (c.cpf = :cpf AND c.cpf <> '') OR " +
            "(c.cnpj = :cnpj AND c.cnpj <> '') OR " +
            "(c.ie = :ie AND c.ie <> '')")
    Optional<Contato> getByDocNumber(String cpf, String cnpj, String ie);

    @Query("SELECT d FROM DadoBancario d JOIN d.contato c WHERE c.id = :id")
    List<DadoBancario> getDadosBancarios(long id);

    @Query("SELECT p FROM ChavePix p JOIN p.contato c WHERE c.id = :id")
    List<ChavePix> getPix(long id);

    @Query("SELECT c FROM Contato c WHERE c.nome ILIKE :nome")
    Optional<Contato> getByNome(String nome);

    Optional <Contato> findByCpf(String cpf);
    Optional<Contato> findByCnpj(String cnpj);
    Optional<Contato> findByIe(String ie);
}
