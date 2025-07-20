package com.alura.br.forum.controller;

import com.alura.br.forum.domain.Topico;
import com.alura.br.forum.dto.topico.DadosListagemTopico;
import com.alura.br.forum.dto.TopicoDto;
import com.alura.br.forum.dto.TopicoDetalhesDto;
import com.alura.br.forum.dto.TopicoAtualizacaoDto;
import com.alura.br.forum.service.TopicoService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<TopicoDetalhesDto> criar(@RequestBody @Valid TopicoDto dados, UriComponentsBuilder uriBuilder) {
        Topico topicoCriado = topicoService.criarTopico(dados);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topicoCriado.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDetalhesDto(topicoCriado));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(@PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao) {

        Page<DadosListagemTopico> page = topicoService.listarTopicos(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDetalhesDto> detalhar(@PathVariable Long id) {
        return topicoService.buscarTopicoPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoDetalhesDto> atualizar(@PathVariable Long id, @RequestBody @Valid TopicoAtualizacaoDto dados) {
        if (!id.equals(dados.id())) {
            return ResponseEntity.badRequest().build();
        }

        return topicoService.atualizarTopico(dados)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        // Delega a lógica de deleção para o TopicoService
        boolean deletado = topicoService.deletarTopico(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}