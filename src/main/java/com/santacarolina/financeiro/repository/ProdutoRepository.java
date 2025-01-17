package com.santacarolina.financeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.dto.ProdutoDuplicataDTO;
import com.santacarolina.financeiro.entity.ProdutoEntity;

/**
 * ProdutoRepository
 */
@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    @Override
    @Query("""
        SELECT produto, documento, classificacao, categoria, contato, pasta
        FROM ProdutoEntity produto
        LEFT JOIN DocumentoEntity documento ON documento.id = produto.documento.id
        LEFT JOIN ClassificacaoEntity classificacao ON classificacao.id = produto.classificacao.id
        LEFT JOIN CategoriaEntity categoria ON categoria.id = classificacao.categoria.id
        LEFT JOIN ContatoEntity contato ON contato.id = documento.contato.id
        LEFT JOIN PastaEntity pasta ON pasta.id = documento.pasta.id
        ORDER BY documento.dataEmissao DESC
        """)
    List<ProdutoEntity> findAll();

    @Query("""
        SELECT produto, documento, classificacao, categoria, contato, pasta
        FROM ProdutoEntity produto
        LEFT JOIN DocumentoEntity documento ON documento.id = produto.documento.id
        LEFT JOIN ClassificacaoEntity classificacao ON classificacao.id = produto.classificacao.id
        LEFT JOIN CategoriaEntity categoria ON categoria.id = classificacao.categoria.id
        LEFT JOIN ContatoEntity contato ON contato.id = documento.contato.id
        LEFT JOIN PastaEntity pasta ON pasta.id = documento.pasta.id
        WHERE produto.documento.id = :documentoId
        """)
    List<ProdutoEntity> findByDoc(long documentoId);

    @Query("""
        SELECT new com.santacarolina.financeiro.dto.ProdutoDuplicataDTO(produto,duplicata), 
            documento, classificacao, categoria, contato, pasta
        FROM ProdutoEntity produto
        JOIN DuplicataEntity duplicata ON produto.documento.id = duplicata.documento.id
        LEFT JOIN DocumentoEntity documento ON documento.id = produto.documento.id
        LEFT JOIN ClassificacaoEntity classificacao ON classificacao.id = produto.classificacao.id
        LEFT JOIN CategoriaEntity categoria ON categoria.id = classificacao.categoria.id
        LEFT JOIN ContatoEntity contato ON contato.id = documento.contato.id
        LEFT JOIN PastaEntity pasta ON pasta.id = documento.pasta.id
        ORDER BY duplicata.dataVencimento DESC
        """)
    List<ProdutoDuplicataDTO> findProdutosDuplicatas();
}
