# Desafio

Criar um crawler que leia [este feed] (http://revistaautoesporte.globo.com/rss/ultimas/feed.xml) e retorne um json estruturado.

# Componentes 

* [h2database](https://github.com/h2database/h2database) - O H2 é um banco de dados Open Source que funciona em memória com um console acessível pelo browser.
* [Docker](https://www.docker.com/) - Para criar o projeto conteinerizado.
* [Spring Boot](https://spring.io/projects/spring-boot) - Para criar uma api REST - V.2.0.6.RELEASE.
* [JWT](https://jwt.io/) - Autenticação com api rest.
* [JSOUP](https://jsoup.org/) - É uma biblioteca Java para trabalhar com HTML.
* [Docker Maven Plugin](https://github.com/spotify/docker-maven-plugin#specify-build-info-in-the-pom) - Um plugin Maven para construir e imagens do Docker.

# Let's go
### Clone o projeto:

```
git clone https://github.com/viniciusv/Crawler.git
```

### Build com Maven:
```
mvn clean package docker:build
```
* O build vai rodar todos os testes da aplicação e vai criar o arquivo **Dorckerfile**.

### Containerize It:
Após o build vá para pasta docker dentro da pasta target
```
Crawler
|-- target/
|   |-- docker/
|   |   |-- Dorckerfile
```
Execute o seguinte comando para
