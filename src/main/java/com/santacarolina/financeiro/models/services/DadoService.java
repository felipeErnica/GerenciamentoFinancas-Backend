package com.santacarolina.financeiro.models.services;

import com.santacarolina.financeiro.models.dto.DadoDTO;
import com.santacarolina.financeiro.repository.ContatoRepository;
import com.santacarolina.financeiro.repository.DadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DadoService {

    private ContatoRepository contatoRepository;
    private DadoRepository dadoRepository;

    @Autowired
    public DadoService(ContatoRepository contatoRepository, DadoRepository dadoRepository) {
        this.contatoRepository = contatoRepository;
        this.dadoRepository = dadoRepository;
    }

    public List<DadoDTO> getDadosBancarios(long id) {
        return contatoRepository.getDadosBancarios(id).stream()
                .map(DadoDTO::new)
                .collect(Collectors.toList());
    }

    public List<DadoDTO> findAll() {
        return dadoRepository.findAll().stream()
                .map(DadoDTO::new)
                .collect(Collectors.toList());
    }

}
