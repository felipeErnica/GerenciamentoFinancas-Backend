package com.santacarolina.financeiro.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santacarolina.financeiro.dto.DuplicataDTO;
import com.santacarolina.financeiro.dto.ProdutoDTO;
import com.santacarolina.financeiro.dto.ProdutoDuplicataDTO;
import com.santacarolina.financeiro.repository.ProdutoRepository;

/**
 * ProdutoDuplicataService
 */
@Service
public class ProdutoDuplicataService {

    @Autowired
    private ProdutoRepository repository;

    public List<ProdutoDuplicataDTO> findProdutosDuplicatas() {
        List<ProdutoDuplicataDTO> produtoDuplicataList = repository.findProdutosDuplicatas();
        
        Map<Long, List<DuplicataDTO>> duplicataMap = produtoDuplicataList.stream()
            .map(prodDup -> prodDup.getDuplicata())
            .collect(Collectors.groupingBy(dup -> dup.getDocumento().getId()));

        Map<Long, List<ProdutoDTO>> produtoMap = produtoDuplicataList.stream()
            .map(prodDup -> prodDup.getProduto())
            .collect(Collectors.groupingBy(prod -> prod.getDocId()));

        Set<Long> documentoSet = produtoMap.keySet();

        for (long documentoId : documentoSet) {
            List<ProdutoDTO> produtoList = produtoMap.getOrDefault(documentoId, Collections.emptyList());
            List<DuplicataDTO> duplicataList = duplicataMap.getOrDefault(documentoId, Collections.emptyList());
            int numDup = duplicataList.size();
            produtoList.forEach(prod -> prod.setQuantidade(prod.getQuantidade()/numDup));
        }

        return produtoDuplicataList;
    }

}
