package com.alura.br.forum.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


        String senhaOriginal = "testes123";
        String senhaCriptografada = encoder.encode(senhaOriginal);

        System.out.println("--- Senha para Teste de Autenticação ---");
        System.out.println("Senha Original: " + senhaOriginal);
        System.out.println("Senha Criptografada (para o banco de dados): " + senhaCriptografada);
        System.out.println("----------------------------------------");


    }
}