# xy-inc

API Restful para cadastrar, listar e encontrar pontos de interesse

Dependências

[Java Development Kit]
[Maven]

Execute o comando para clonar o projeto:

git clone https://github.com/haseu/xy-inc.git

Banco de dados
Utilizado Banco H2 para facilitar a configuração de ambiente

Inicializando a aplicação
Inicie a aplicação com o comando:

mvn spring-boot:run
A aplicação será acessível na URL:

http://localhost:8080/zup

Testes automatizados
Execute os testes da aplicação com o comando:

mvn clean test

| Endpoints                                    |   Method      | Consumes  |  Produces  | Result                                                            |
| ---------------------------------------------|:-------------:|:---------:|:----------:|------------------------------------------------------------------:|
| /api/poi?page=size=                          |    GET        |     -     |    JSON    |Lista todos os pontos inseridos                                    |
| /api/poi                                     |    POST       |   JSON    |     -      |Insere um novo ponto                                               |
| /api/poi/nearby/poi/nearby?page=&size=&x=&y= |    GET        |    -      |    JSON    |Busca os pontos de interesse baseado em uma localização fornecida  |

Json para adicionar pontos de interesse
{
  "name" : "",
  "coordinateX" : ,
  "coordinateY" : 
}