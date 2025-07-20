package com.alura.br.forum.domain;

import com.alura.br.forum.dto.TopicoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String status;
    private String autor;
    private String curso;



    public Topico(String titulo, String mensagem, String autor, String curso) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.dataCriacao = LocalDateTime.now();
        this.status = "ABERTO"; // Status padrão
        this.autor = autor;
        this.curso = curso;
    }


    public Topico(TopicoDto dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.autor = dados.autor();
        this.curso = dados.curso();
        this.dataCriacao = LocalDateTime.now();
        this.status = "ABERTO"; // Status inicial
    }


    public void atualizarInformacoes(String titulo, String mensagem, String autor, String curso) {
        if (titulo != null && !titulo.isBlank()) {
            this.titulo = titulo;
        }
        if (mensagem != null && !mensagem.isBlank()) {
            this.mensagem = mensagem;
        }
        if (autor != null && !autor.isBlank()) {
            this.autor = autor;
        }
        if (curso != null && !curso.isBlank()) {
            this.curso = curso;
        }
    }


    public void fecharTopico() {
        this.status = "FECHADO";
    }
}