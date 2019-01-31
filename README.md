**Star Wars Planet**
====================


É um projeto desenvolvido como desafio para criar um conjunto de operações (api) que permitam o cadastramento dos planetas mencionados nos filmes Star Wars



Arquitetura Básica dos Projetos
-----------------------------------



### **Domínio**

As classes do diagrama abaixo representam o modelo de domínio básico definido para os projetos.




![](images/diagrama_classe.png)



### **Request Padrão**



![](images/request_padrao.svg)



-   No projeto starwarsplanet os request seguem o padrão descrito no diagrama acima.



### **Controladores**

No projeto starwarsplanet o controlador foi implementado através da classe:



![](images/controladores.png)



### **Services**

Os serviços de negócio são acessados através de interfaces que são implementadas seguindo basicamente o exemplo do diagrama abaixo:




![](images/services.png)



No projeto definiu-se uma classe abstrata BaseService.java que implementa alguns métodos que que são reaproveitados por todos os serviços.



### **Persistência**


A camada de persistência do projeto foi implementada através do Spring Data JPA. As interfaces anotadas com @Repository definem as operações necessárias.

 

### Segurança

Requisitos de segurança foram desconsiderados para esse projeto.



**Documentação da Api REST.**
-----------------------------


A documentação (resumida) da api rest implementada pelo projeto pode ser consultada após o projeto ser iniciado, através da url: http://localhost:8080/swagger-ui.html#/planet-resource

 

![](images/documentacao_api.png)

  



**Executando os projetos**
--------------------------

Para executar os projetos podem ser utilizados os seguintes comandos, após
baixar o zip do projeto.

1.  Ir para o diretório específico da aplicação

2.  Executar o comando: mvn spring-boot:run

3.  Os dois projetos deve estar iniciados.

4.  A url é: http://localhost:8080/api/planets

 

### **Carga no Banco de Dados**

 A carga no banco de dados na inicialização do projeto é realizada com a execução do arquivo import.sql.

```sql
INSERT INTO PLANET(ID, NAME, CLIMATE, TERRAIN) VALUES(3, 'PLANETA 3', 'CLIMATE 3', 'TERRAIN 3');
INSERT INTO PLANET(ID, NAME, CLIMATE, TERRAIN) VALUES(4, 'Yavin IV', 'temperate, tropical', 'jungle, rainforests');

```



### Testes

Foi incluído no projeto o arquivo "Desafio B2W.postman_collection.json". Este arquivo poderá ser utilizado para realização de testes da api através do software Postman (https://www.getpostman.com/). Para executar a sequência de testes deve sempre reiniciar a aplicação (mvn spring-boot:run)


![](/images/postman.png)
