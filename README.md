**HACKATHON SOAT - Agenda-app**

Projeto responsável pela gestão de horários da agenda dos médicos. No projeto é possivel: 

 - Criar/Alterar agenda. 
 - Lista horarios por dia e médico
 - Realizar agendamento de consulta.

**Tecnologias**: 
 - Projeto em Java 17 
 - Spring-boot 3 Mariadb
 -  Path: /agenda-app
-   Porta: 8080
 

**Pipeline** 
-	Testes -> Sonar -> Build -> Docker -> Deploy no EKS

---

- Mariadb 
```
$ docker run --detach --name mariadb-agenda-db -p 3306:3306 --env MARIADB_DATABASE=agendadb --env  MARIADB_USER=mariadb --env MARIADB_PASSWORD=root --env MARIADB_ROOT_PASSWORD=root mariadb:latest
```
---

- Cognito - Criar paciente ou médico
```
https://usuarios-lanchonete-app.auth.us-east-1.amazoncognito.com/oauth2/authorize?client_id=3fi6en90td7gifec51799obfbd&response_type=token&scope=email+openid+phone&redirect_uri=https%3A%2F%2Fexample.com%2F
```
---

- Lambda  - Login paciente ou médico
```
https://9l6e6av4i5.execute-api.us-east-1.amazonaws.com/Prod/auth?username={}&password={}

```

**Swagger**

[Link Swagger](http://localhost:8080/agenda-app/swagger-ui/index.html)
