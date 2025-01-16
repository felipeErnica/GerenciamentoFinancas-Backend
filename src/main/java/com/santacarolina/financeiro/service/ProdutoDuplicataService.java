package com.santacarolina.financeiro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santacarolina.financeiro.dto.ProdutoDuplicataDTO;
import com.santacarolina.financeiro.entity.DuplicataEntity;
import com.santacarolina.financeiro.entity.ProdutoEntity;
import com.santacarolina.financeiro.repository.DuplicataRepository;
import com.santacarolina.financeiro.repository.ProdutoRepository;

/**
 * ProdutoDuplicataService
 */
@Service
public class ProdutoDuplicataService {

    private ProdutoRepository produtoRepository;
    private DuplicataRepository duplicataRepository;

    @Autowired
    public ProdutoDuplicataService(ProdutoRepository produtoRepository, DuplicataRepository duplicataRepository) {
        this.produtoRepository = produtoRepository;
        this.duplicataRepository = duplicataRepository;
    }

    private void addElements(
        List<ProdutoEntity> produtoEntities, 
        List<DuplicataEntity> duplicataEntities, 
        List<ProdutoDuplicataDTO> produtoDuplicataList,
        int index) {

        if (index == produtoEntities.size()) return;
        ProdutoEntity produto = produtoEntities.get(index);
        
        List<DuplicataEntity> filterDuplicatas = duplicataEntities.stream()
            .filter(dup -> dup.getDocumento().getId() == produto.getDocumento().getId())
            .toList();
        produto.setValorUnit(produto.getValorUnit()/filterDuplicatas.size());

        for (DuplicataEntity dup : filterDuplicatas) {
            ProdutoDuplicataDTO dto = new ProdutoDuplicataDTO(produto, dup);
            produtoDuplicataList.add(dto);
        }

        index++;
        System.out.println(index);
        addElements(produtoEntities, duplicataEntities, produtoDuplicataList, index);
    }

    private List<ProdutoDuplicataDTO> getProdutoDuplicataList(List<ProdutoEntity> produtoEntities, List<DuplicataEntity> duplicataEntities) {
        List<ProdutoDuplicataDTO> produtoDuplicataList = new ArrayList<>();
        addElements(produtoEntities, duplicataEntities, produtoDuplicataList, 0);
        return produtoDuplicataList;
    }

    public List<ProdutoDuplicataDTO> findProdutosDuplicatas() {
        List<ProdutoEntity> produtoEntities = produtoRepository.findAll();
        List<DuplicataEntity> duplicataEntities = duplicataRepository.findAll();
        return getProdutoDuplicataList(produtoEntities, duplicataEntities);
    }

}
