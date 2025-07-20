package com.alura.br.forum.service;

import com.alura.br.forum.domain.Topico;
import com.alura.br.forum.dto.TopicoDto;
// import com.alura.br.forum.dto.TopicoListagemDto;
import com.alura.br.forum.dto.TopicoDetalhesDto;
import com.alura.br.forum.dto.TopicoAtualizacaoDto;
import com.alura.br.forum.repository.TopicoRepository;
import com.alura.br.forum.dto.topico.DadosListagemTopico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;


@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Transactional
    public Topico criarTopico(TopicoDto dados) {

        Topico topico = new Topico(dados);
        return topicoRepository.save(topico);
    }


    public Page<DadosListagemTopico> listarTopicos(Pageable paginacao) {
        return topicoRepository.findAllByOrderByDataCriacaoAsc(paginacao);
    }

    public Optional<TopicoDetalhesDto> buscarTopicoPorId(Long id) {
        return topicoRepository.findById(id)
                .map(TopicoDetalhesDto::new);
    }

    @Transactional
    public Optional<TopicoDetalhesDto> atualizarTopico(TopicoAtualizacaoDto dados) {
        Optional<Topico> optionalTopico = topicoRepository.findById(dados.id());

        if (optionalTopico.isPresent()) {
            Topico topico = optionalTopico.get();

            topico.atualizarInformacoes(dados.titulo(), dados.mensagem(), dados.autor(), dados.curso());
            if (dados.status() != null && !dados.status().isBlank()) {
                topico.fecharTopico();
            }

            return Optional.of(new TopicoDetalhesDto(topico));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public boolean deletarTopico(Long id) {
        if (topicoRepository.existsById(id)) {
            topicoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}