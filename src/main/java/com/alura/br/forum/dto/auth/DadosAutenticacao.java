package com.alura.br.forum.dto.auth;

import jakarta.validation.constraints.NotBlank;


public record DadosAutenticacao(
        @NotBlank
        String login,
        @NotBlank
        String senha
) {
}