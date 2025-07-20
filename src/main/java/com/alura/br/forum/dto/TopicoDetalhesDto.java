package com.alura.br.forum.dto;

import com.alura.br.forum.domain.Topico;
import java.time.LocalDateTime;

public record TopicoDetalhesDto(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        String status,
        String autor,
        String curso
) {

    public TopicoDetalhesDto(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso()
        );
    }
}
