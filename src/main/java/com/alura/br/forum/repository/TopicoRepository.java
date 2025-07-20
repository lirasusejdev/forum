package com.alura.br.forum.repository;

import com.alura.br.forum.domain.Topico;
import com.alura.br.forum.dto.topico.DadosListagemTopico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {


    Page<DadosListagemTopico> findAllByOrderByDataCriacaoAsc(Pageable paginacao);
    // Page<DadosListagemTopico> findAll(Pageable paginacao);
}