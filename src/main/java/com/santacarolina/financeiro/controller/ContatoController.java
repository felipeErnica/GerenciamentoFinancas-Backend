package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.dto.DadoDTO;
import com.santacarolina.financeiro.dto.PixDTO;
import com.santacarolina.financeiro.services.DadoService;
import com.santacarolina.financeiro.models.Contato;
import com.santacarolina.financeiro.services.PixService;
import com.santacarolina.financeiro.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    private ContatoRepository repository;
    private DadoService dadoService;
    private PixService pixService;

    @Autowired
    public ContatoController(ContatoRepository repository, DadoService dadoService, PixService pixService) {
        this.repository = repository;
        this.dadoService = dadoService;
        this.pixService = pixService;
    }

    @GetMapping
    public List<Contato> getAll(){ return repository.findAll(); }

    @GetMapping("/doc")
    public Contato getByDocNumber(@RequestParam("cpf") Optional<String> paramCpf,
                                  @RequestParam("cnpj") Optional<String> paramCnpj,
                                  @RequestParam("ie") Optional<String> paramIe) {
        String cpf = paramCpf.orElse(null);
        String cnpj = paramCnpj.orElse(null);
        String ie = paramIe.orElse(null);
        return repository.getByDocNumber(cpf,cnpj,ie).orElse(null);
    }

    @GetMapping("/cpf={cpf}")
    public ResponseEntity<Contato> findByCpf(@PathVariable String cpf) {
        return repository.findByCpf(cpf).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/cnpj={cnpj}")
    public ResponseEntity<Contato> findByCnpj(@PathVariable String cnpj) {
        return repository.findByCnpj(cnpj).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/ie={ie}")
    public ResponseEntity<Contato> findByIe(@PathVariable String ie) {
        return repository.findByIe(ie).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public Contato getContato(@PathVariable long id){ return repository.findById(id).orElse(null); }
    @GetMapping("/info")
    public Contato getByNome(@RequestParam String nome) { return repository.getByNome(nome).orElse(null); }
    @GetMapping("/{id}/contas")
    public List<DadoDTO> getDadosBancarios(@PathVariable long id) { return dadoService.getDadosBancarios(id); }
    @GetMapping("/{id}/pix")
    public List<PixDTO> getPix(@PathVariable long id) { return pixService.getPixByContato(id); }
    @PostMapping("/batch")
    public void addContato(@RequestBody List<Contato> contatos){ repository.saveAll(contatos); }
    @PostMapping
    public void addContato(@RequestBody Contato contato){ repository.save(contato); }
    @DeleteMapping("/{id}")
    public void deleteContato(@PathVariable long id){ repository.deleteById(id); }

}