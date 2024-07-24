
- Criação do banco de dados

```
$ docker run --detach --name mariadb-agenda-db -p 3306:3306 --env MARIADB_DATABASE=agendadb --env  MARIADB_USER=mariadb --env MARIADB_PASSWORD=root --env MARIADB_ROOT_PASSWORD=root mariadb:latest
```

- Cognito
```
https://usuarios-lanchonete-app.auth.us-east-1.amazoncognito.com/oauth2/authorize?client_id=3fi6en90td7gifec51799obfbd&response_type=token&scope=email+openid+phone&redirect_uri=https%3A%2F%2Fexample.com%2F
```

