# Desafio

Criar um crawler que leia este [feed](http://revistaautoesporte.globo.com/rss/ultimas/feed.xml) e retorne um Json estruturado.

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
Execute o seguinte comando para executar a imagem do docker:
```
docker run -it -p 9999:8080 crawler_docker
```

### API:
Acessar a url:
```
http://localhost:9999/login
```
* Para realizar esses testes na api aconselho usar o [Postman](https://www.getpostman.com/), para enviar um JSON de credenciais.
```
{
  "userName" : "userFirst",
  "password": "123AbC8jO"
}
```

Depois do login realizado, acessar a url abaixo, para receber o Json da página:
```
http://localhost:9999/crawler
```
Exemplo:
```
{
    "items": [
        {
            "title": "Quais as diferenças entre farol alto, baixo, de milha, de neblina e DRL?",
            "link": "https://revistaautoesporte.globo.com/Noticias/noticia/2018/11/quais-diferencas-entre-farol-alto-baixo-de-milha-de-neblina-e-drl.html",
            "descriptions": [
                {
                    "type": "image",
                    "content": [
                        "https://s2.glbimg.com/7ez_1-JZdT9-ltumgwDX1qyTd-4=/620x413/e.glbimg.com/og/ed/f/original/2018/11/09/l200tritonfrente.jpg"
                    ]
                },
                {
                    "type": "image",
                    "content": [
                        "https://s2.glbimg.com/OIVAeWL0B4PqfZMsaN6vlORhz9Y=/620x413/e.glbimg.com/og/ed/f/original/2018/08/07/comparativoledphilips_tompapp6.jpg"
                    ]
                },
                {
                    "type": "text",
                    "content": [
                        "Luzes de posição Popularmente conhecida como “lanterna”, elas são obrigatórias em duas ocasiões: com o veículo parado na via, para embarque e desembarque de passageiros ou carga e descarga; ou durante o dia, sob chuva."
                    ]
                },
                {
                    "type": "text",
                    "content": [
                        "Faróis de milha e neblina Embora suas funções sejam bem distintas, muita gente os confunde - e, em certa situações, isso pode até ser perigoso."
                    ]
                },
                {
                    "type": "links",
                    "content": [
                        "https://revistaautoesporte.globo.com/Servico/noticia/2018/10/oficina-posso-trocar-medidas-de-rodas-e-pneus-do-meu-carro.html",
                        "https://revistaautoesporte.globo.com/Oficina/noticia/2018/05/oficina-dirigir-o-carro-em-ponto-morto-economiza-combustivel.html",
                        "https://revistaautoesporte.globo.com/Oficina/noticia/2017/12/oficina-como-montadoras-medem-potencia-e-o-torque-de-um-motor.html"
                    ]
                }
            ]
        }
    ]
}
```
