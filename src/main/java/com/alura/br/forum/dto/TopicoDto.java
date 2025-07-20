package com.alura.br.forum.dto;

import jakarta.validation.constraints.NotBlank;

public record TopicoDto(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotBlank
        String autor,
        @NotBlank
        String curso) {
}