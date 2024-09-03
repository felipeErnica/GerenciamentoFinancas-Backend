package com.santacarolina.financeiro.models.services;

import com.santacarolina.financeiro.models.dto.PixDTO;
import com.santacarolina.financeiro.models.entities.ChavePix;
import com.santacarolina.financeiro.repository.ContatoRepository;
import com.santacarolina.financeiro.repository.PixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PixService {

    private ContatoRepository contatoRepository;
    private PixRepository pixRepository;

    @Autowired
    public PixService(ContatoRepository contatoRepository, PixRepository pixRepository) {
        this.contatoRepository = contatoRepository;
        this.pixRepository = pixRepository;
    }

    public List<PixDTO> getPixByContato(long id) {
        return contatoRepository.getPix(id).stream()
                .map(PixDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<PixDTO> findByChavePix (String chave) {
        return pixRepository.findByChave(chave).map(PixDTO::new);
    }

    public void save(ChavePix chavePix) {
        pixRepository.save(chavePix);
    }

    public List<PixDTO> findAll() {
        return pixRepository.findAll().stream()
                .map(PixDTO::new)
                .collect(Collectors.toList());
    }
}
