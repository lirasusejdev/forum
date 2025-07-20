package com.alura.br.forum.dto.topico;

import java.time.LocalDateTime;

public interface DadosListagemTopico {
    Long getId();
    String getTitulo();
    String getMensagem();
    LocalDateTime getDataCriacao();
    String getStatus();
    String getAutor();
    String getCurso();
}