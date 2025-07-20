
<img width="1500" height="750" alt="Programação-Forum Hub API REST" src="https://github.com/user-attachments/assets/0525361f-d7ee-4246-8b04-e93a6f9119ef" />

# 🚀 Forum Hub API REST

Bem-vindo(a) a API REST do Forum Hub\! Este projeto faz parte do desafio "Forum Hub" da Alura, construído com Spring Boot 3, e visa simular um fórum de discussões, oferecendo endpoints para gerenciar tópicos, usuários e cursos.



-----

## ✨ Tecnologias Utilizadas

  * **Java 17:** Linguagem de programação.
  * **Spring Boot 3.3.1:** Framework para desenvolvimento rápido de APIs REST.
      * **Spring Data JPA:** Para persistência de dados e interação com o banco.
      * **Spring Security:** Para autenticação e autorização (com JWT).
      * **Spring Web:** Para construir os endpoints REST.
  * **MySQL:** Banco de dados relacional.
  * **Lombok:** Para reduzir o boilerplate code (getters, setters, construtores).
  * **Flyway:** Para controle de versão do banco de dados (gerenciamento de migrations).
  * **Springdoc OpenAPI UI (Swagger):** Para documentação interativa da API.
  * **JWT (JSON Web Token):** Para segurança e autenticação stateless.
  * **Maven:** Gerenciador de dependências e build do projeto.
  * **Insomnia/Postman:** Para testes de endpoints.

-----

## 📋 Funcionalidades da API

A API Forum Hub oferece os seguintes recursos:

  * **Autenticação de Usuários:**
      * `POST /login`: Realiza o login e retorna um **token JWT** para acesso aos endpoints protegidos.
  * **Gerenciamento de Tópicos:**
      * `POST /topicos`: Cria um novo tópico (requer autenticação).
      * `GET /topicos`: Lista todos os tópicos existentes, com **paginação e projeção de dados** otimizada (requer autenticação).
      * `GET /topicos/{id}`: Detalha um tópico específico (requer autenticação).
      * `PUT /topicos/{id}`: Atualiza um tópico existente (requer autenticação).
      * `DELETE /topicos/{id}`: Exclui um tópico (requer autenticação).
      * *Outras funcionalidades (se implementadas):* Gerenciamento de cursos e usuários (a API foi expandida para incluir um controller/service para eles).

-----

## 🚀 Como Rodar o Projeto

Siga os passos abaixo para configurar e executar a API localmente:

### Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas:

  * **JDK 17** ou superior.
  * **Maven** 3.6.x ou superior.
  * **MySQL Server** 8.0 ou superior.
  * Uma IDE como **IntelliJ IDEA** (recomendado).

### Configuração do Banco de Dados

1.  **Crie um banco de dados MySQL** com o nome que preferir (ex: `forum_hub`).

2.  **Atualize as credenciais do banco de dados** no arquivo `src/main/resources/application.properties`:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/forum_hub
    spring.datasource.username=seu_usuario_mysql
    spring.datasource.password=sua_senha_mysql
    ```

3.  As migrations do **Flyway** (`db/migration`) criarão as tabelas necessárias automaticamente no primeiro startup da aplicação.

### Executando a Aplicação

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/seu-usuario/seu-repositorio.git
    cd seu-repositorio
    ```
2.  **Abra o projeto na sua IDE (IntelliJ IDEA):**
      * Vá em `File` -\> `Open` e selecione a pasta raiz do projeto.
3.  **Sincronize as dependências Maven:**
      * No IntelliJ, clique no ícone do Maven (geralmente à direita) e selecione "Reimport All Maven Projects".
4.  **Execute a aplicação:**
      * Localize a classe principal `ForumApplication.java` (em `src/main/java/com/alura/br/forum`) e execute-a (clique no ícone de "play" verde ao lado do `main` method).
      * Alternativamente, via terminal na raiz do projeto: `mvn spring-boot:run`

A API estará rodando em `http://localhost:8082`.

-----

## 📄 Documentação da API (Swagger UI)

Após iniciar a aplicação, você pode acessar a documentação interativa da API através do Swagger UI no seu navegador:

  * **URL:** `http://localhost:8082/swagger-ui.html`

Nesta interface, você poderá visualizar todos os endpoints, testá-los diretamente (incluindo autenticação com JWT) e entender a estrutura das requisições e respostas.

-----

## 🚧 Desafios e Soluções (Jornada de Aprendizado)

Durante o desenvolvimento deste projeto, enfrentei alguns desafios técnicos significativos, que foram cruciais para o aprendizado e aprofundamento em Spring Boot e Maven.

### 1\. `java.lang.NoSuchMethodError: 'void org.springframework.web.method.ControllerAdviceBean.<init>(java.lang.Object)'` no Swagger UI

  * **Problema:** Um erro `NoSuchMethodError` impedia o Swagger UI de carregar, mesmo com as dependências aparentemente corretas.
  * **Causa Raiz:** A versão do `spring-boot-starter-parent` no `pom.xml` estava configurada para `3.5.3`, uma **versão futura e inexistente** do Spring Boot. Isso causava falhas na resolução de dependências internas do Spring Framework, levando a incompatibilidades em tempo de execução.
  * **Solução:** Ajustar a versão do `spring-boot-starter-parent` para uma **versão estável e existente**, como `3.3.1`. Isso permitiu que o Maven resolvesse corretamente todas as dependências transitivas do Spring Boot e Springdoc, eliminando o conflito.

### 2\. Conflitos de Dependências Transitivas

  * **Problema:** Mesmo após a correção da versão do parent, houve uma insistência de erros de compatibilidade, indicando que alguma dependência transitiva poderia estar trazendo versões antigas de bibliotecas do Spring.

  * **Causa Raiz:** Dependências como `springdoc-openapi-starter-webmvc-ui` podem, por padrão, trazer suas próprias versões de bibliotecas do Spring Framework, potencialmente conflitando com as gerenciadas pelo `spring-boot-starter-parent`.

  * **Solução:** Utilizar `<exclusions>` na declaração da dependência `springdoc-openapi-starter-webmvc-ui` no `pom.xml`, excluindo os grupos `org.springframework` e `org.springframework.boot`. Isso força o projeto a usar **apenas as versões do Spring e Spring Boot gerenciadas pelo `spring-boot-starter-parent`**.

    ```xml
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.5.0</version>
        <exclusions>
            <exclusion>
                <groupId>org.springframework</groupId>
                <artifactId>*</artifactId>
            </exclusion>
            <exclusion>
                <groupId>org.springframework.boot</groupId>
                <artifactId>*</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    ```

### 3\. `No property 'nome' found for type 'String'; Traversed path: Topico.autor` na Projeção

  * **Problema:** Ao implementar o Spring Data JPA Projection para listar tópicos de forma otimizada, a aplicação falhava ao tentar projetar os campos `autor` e `curso`.
  * **Causa Raiz:** A entidade `Topico` modelava `autor` e `curso` como campos do tipo `String` (`private String autor;`). No entanto, a interface de projeção (`DadosListagemTopico`) tentava acessar `getAutorNome()` e `getCursoNome()`, implicando que `autor` e `curso` seriam objetos complexos (como `Usuario` ou `Curso`) com uma propriedade `nome`.
  * **Solução:** Alinhar a interface de projeção com a estrutura real da entidade `Topico`. Os métodos `getAutorNome()` e `getCursoNome()` foram alterados para **`getAutor()` e `getCurso()`** na interface `DadosListagemTopico`, permitindo que o Spring Data JPA projetasse diretamente os campos `String` da entidade.

-----

## 🤝 Contribuições

Contribuições são bem-vindas\! Se você encontrar bugs, tiver sugestões de melhoria ou quiser adicionar novas funcionalidades, sinta-se à vontade para abrir uma *issue* ou enviar um *pull request*.

-----

## 📜 Licença

Este projeto está licenciado sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

-----
