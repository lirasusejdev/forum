package com.alura.br.forum.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoAtualizacaoDto(
        @NotNull
        Long id,
        // Estes campos não usam @NotBlank porque são opcionais para atualização.
        // Se o usuário não enviar, o valor existente no BD será mantido.
        String titulo,
        String mensagem,
        String autor,
        String curso,
        String status
) {
}
