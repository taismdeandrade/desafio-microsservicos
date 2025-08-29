
# Desafio Microsserviços
## O sistema conta com 3 microsserviços.

## [1. Cadastro](#1-microsserviço-de-cadastro)
1. Realiza o cadastro.
2. Integração com API externa.
3. Envio de informações para a Agenda.

## [2. Agenda](#2-microsserviço-de-agendamento)
1. Cria agendamentos.
2. Integração com o cadastro.
3. integração com a notificação.

## [3. Notificação](#3-microsserviço-de-notificação)
1. Envio de notificações.
2. Integração com a agenda.



                                    


## 1. Microsserviço de Cadastro
- Cadastro de pets com informações do pet e do dono.
- Listar os pets cadastrados.
- Filtros de consulta por raça, espécie ou id.
- Atualizar o registro de um pet passando seu id.
- Excluir um pet cadastrado passando seu id.

## 2. Microsserviço de Agendamento
- Realiza o cadastro de agendamento de cuidados para os pets.
- Busca informações dos pets no microsserviço de cadastros.
- Envia informações de agendamento para o serviço de notificação.

## 3. Microsserviço de Notificação
- Recebe as informações de agendamentos.
- Envio de e-mail com as informações do agendamento.

                                    
## Configuração para rodar o  projeto
### Pré-requisitos
- Java 21 ou superior.
- MySQL instalado e configurado.
- WSL com distro linux.
- Docker.
- Uma Api key da [TheCatAPI](https://thecatapi.com/) e [TheDogAPI](https://thedogapi.com/) para o microsserviço de cadastro buscar imagens.


### Passos para rodar os serviços
1. Clone o repositório
   - `$git clone git@github.com:taismdeandrade/desafio-microsservicos.git`
   - Acesse a pasta do projeto: `cd desafio-microsservicos`

2. Configure o banco de dados
   - Crie um banco de dados com nome `cadastro_db` para o microsserviço de cadastro.
     - Atualize o usuario e senha no `application.properties` do microsserviço de cadastro.
   - Crie um banco de dados com nome `agenda_db` para o microsserviço de agendamento.
      - Atualize o usuario e senha no `application.properties` do microsserviço de agenda.

3. Configure as chaves da api externa
   - No arquivo application.properties do microsserviço de cadastro, procure por `apikey = ${API_KEY}` e mude o valor `${API_KEY}` para a sua chave.

5. Configure usuario e senha do rabbitmq
   - No arquivo docker-compose.yml procure por `RABBITMQ_DEFAULT_USER: ` e `RABBITMQ_DEFAULT_PASS: ` e insira suas credenciais.

6. Configure o e-mail para o envio das notificações.
   - No arquivo application.properties do microsserviço de notificação, procure por `spring.mail.username=${username}` e
     `spring.mail.password=${mailpassword}` e configure com suas credenciais.
     - Caso utilize um email do Gmail, será preciso criar uma senha de app nas configurações de segurança de sua conta. 

7. Usando o terminal da distro linux, execute o comando para iniciar o docker:
   - `sudo service docker start` 
   - Insira sua senha de root.
   - Vá até a pasta onde está o projeto. ex `cd /mnt/c/User/usuario/desafio-microsservicos`.
   - Digite o comando `docker compose-up` para criar o container do rabbitmq.
   - Acesse o rabbitmq management através do `localhost:1572`. 
   

8. Dê start na aplicação de cadastro.


9. Dê start na aplicação de agenda.


10. Dê start na aplicação de notificação.


11. Para testar o cadastro via swagger acesse `http://localhost:8080/swagger-ui/index.html#/`.


12. Para testar o agendamento via swagger acesse `http://localhost:8082/swagger-ui/index.html#/`.


13. Para testar via Postman
- No postaman, clique em import e cole o arquivo Json `Desafio MS.postman_collection.json`.



                                   
## Tecnologias Utilizadas
- [Java](https://www.java.com/pt-BR/)
- [OpenJdk 21](https://openjdk.org/projects/jdk/21/)
- [Spring Boot](https://spring.io/)
- [MySQL Community](https://dev.mysql.com/downloads/)
- [TheCatAPI](https://thecatapi.com/)
- [TheDogAPI](https://thedogapi.com/)
- [WSL](https://learn.microsoft.com/en-us/windows/wsl/install)
- [Docker](https://www.docker.com/)
- [RabbitMQ](https://www.rabbitmq.com/)
- [Swagger](https://swagger.io/specification/)
- [RestTemplate](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html)
- [Maven](https://mvnrepository.com/)
